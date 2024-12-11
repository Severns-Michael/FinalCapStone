using Microsoft.EntityFrameworkCore;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.IdentityModel.Tokens;
using System.Text;
using Microsoft.AspNetCore.Authorization;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using Microsoft.AspNetCore.Mvc;
using Jose;
using System.Security.Cryptography;
using PetTinder.Service;
using System.Reflection.Metadata.Ecma335;
using System.Linq;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;


var builder = WebApplication.CreateBuilder(args);
builder.Services.AddDbContext<PTDb>();
//Only do this in dev
builder.Services.AddCors(options =>
{
    options.AddPolicy("AllowAllOrigins",
        policyBuilder => policyBuilder.AllowAnyHeader()
            .AllowAnyMethod()
            .AllowAnyOrigin()
            //.AllowCredentials()
            .SetIsOriginAllowed(_ => true)
    );
});
builder.Services.AddAuthentication(options =>
{
    options.DefaultAuthenticateScheme = JwtBearerDefaults.AuthenticationScheme;
    options.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
    options.DefaultScheme = JwtBearerDefaults.AuthenticationScheme;
}).AddJwtBearer(o =>
{
    o.TokenValidationParameters = new TokenValidationParameters
    {
        ValidIssuer = builder.Configuration["Jwt:Issuer"],
        ValidAudience = builder.Configuration["Jwt:Audience"],
        IssuerSigningKey = new SymmetricSecurityKey
            (Encoding.UTF8.GetBytes(builder.Configuration["Jwt:Key"])),
        ValidateIssuer = true,
        ValidateAudience = true,
        ValidateLifetime = true,
        ValidateIssuerSigningKey = true,
    };
   
    
});
builder.Services.AddAuthorization();
var app = builder.Build();
app.UseAuthentication();
app.UseAuthorization();
app.UseCors("AllowAllOrigins"); //this is the other side of the CORS



#region Traits
app.MapGet("/traits", (PTDb db) => db.Traits.ToList());
app.MapGet("/traits/{id}", (PTDb db, int id) => db.Traits.Where(t => t.traitId == id).FirstOrDefault()==null?new Trait(): db.Traits.Where(t => t.traitId == id).First());
app.MapGet("/traits/include", (PTDb db, HttpContext http) =>
{
    var userName = http.User.FindFirstValue(ClaimTypes.Email);
    var usr = db.Users.Where(u => u.userName == userName).Include(u => u.UserTraits).ThenInclude(t => t.Trait).FirstOrDefault();

    
    List<UserTrait> trts = usr.UserTraits.Where(t=>t.isYes).ToList();
    List<Trait> ret = (from tr in trts
                       select tr.Trait).ToList();
    return ret;
} );
app.MapGet("/traits/exclude", (PTDb db, HttpContext http) =>
{
    var userName = http.User.FindFirstValue(ClaimTypes.Email);
    var usr = db.Users.Where(u => u.userName == userName).Include(u => u.UserTraits).ThenInclude(t => t.Trait).FirstOrDefault();


    List<UserTrait> trts = usr.UserTraits.Where(t => t.isYes==false).ToList();
    List<Trait> ret = (from tr in trts
                       select tr.Trait).ToList();
    return ret;
});

app.MapPost("/traits/include",async (PTDb db, List<Trait> traits, HttpContext http) =>
{
    //Delete all the existing Yes Traits and replace them with this
    var userName = http.User.FindFirstValue(ClaimTypes.Email);
    //User? usr = db.Users.Where(u => u.userName == userName).Include("UserTraits").Include("UserTrait.Trait").FirstOrDefault(); //this is an alternate way to do the following
    User? usr = await db.Users.Where(u => u.userName == userName).Include(u=>u.UserTraits).ThenInclude(t=>t.Trait).FirstOrDefaultAsync();
    usr.UserTraits.RemoveAll(u => u.isYes == true);
    //List<UserTrait> newTraits = new List<UserTrait>();
    foreach(Trait t in traits)
    {
        UserTrait newTrait = (new UserTrait()
        {
            userId = usr.userId,
            traitId = t.traitId,
            isYes=true
        });
        db.UserTraits.Add(newTrait);
    }
    
    db.SaveChanges();
}).RequireAuthorization();

app.MapPost("/traits/exclude", async (PTDb db, List<Trait> traits, HttpContext http) =>
{
    //Delete all the existing No Traits and replace them with this
    var userName = http.User.FindFirstValue(ClaimTypes.Email);
    //User? usr = db.Users.Where(u => u.userName == userName).Include("UserTraits").Include("UserTrait.Trait").FirstOrDefault(); //this is an alternate way to do the following
    User? usr = await db.Users.Where(u => u.userName == userName).Include(u => u.UserTraits).ThenInclude(t => t.Trait).FirstOrDefaultAsync();
    usr.UserTraits.RemoveAll(u => u.isYes == false);
    //List<UserTrait> newTraits = new List<UserTrait>();
    foreach (Trait t in traits)
    {
        UserTrait newTrait = (new UserTrait()
        {
            userId = usr.userId,
            traitId = t.traitId,
            isYes = false
        });
        db.UserTraits.Add(newTrait);
    }

    db.SaveChanges();
}).RequireAuthorization();


#endregion

#region Breed

app.MapGet("/breeds/{breedId}", (PTDb db, int id) =>  db.Breeds.Where(b => b.breedId == id).FirstOrDefault());
app.MapGet("/breeds", (PTDb db) => db.Breeds.Include(b=>b.traits).ToList());
app.MapPost("/breeds", (PTDb db, [FromBody] Breed breed) =>
{
    //Just the breed
    db.Breeds.Add(breed);
    db.SaveChanges();
});

app.MapPut("/breeds", (PTDb db, [FromBody] Breed breed) =>
{
    //All the children too
    db.Breeds.Update(breed);
    db.SaveChanges();
});

//I messed this up, you guys only submit an id but I thought it was the whole breed object
app.MapDelete("/breeds/", (PTDb db, [FromBody] Breed breed) =>
{
    db.Breeds.Remove(breed);
    db.SaveChanges();
});

app.MapDelete("/breeds/{id}", (PTDb db,int id) =>
{
    Breed? brd = db.Breeds.Where(b => b.breedId == id).FirstOrDefault();
    if (brd != null)
    {
        db.Breeds.Remove(brd);
        db.SaveChanges();
    }
});

app.MapGet("breeds/random", (PTDb db, HttpContext http)=>
{
    //This is going to be tricky, I'll have to rebuild the sql
    //Get the user
    var userName = http.User.FindFirstValue(ClaimTypes.Email);
    var usr = db.Users.Where(u => u.userName == userName).Include(u => u.UserTraits).ThenInclude(t => t.Trait).FirstOrDefault();
    //Get all breeds and traits
    //I had to cheat on this because Postgres sucks so bad, this is how you write a SQL query in Entity so you can use it to adapt the rest of the query
    var breedId = 3;
    var breeds = db.Breeds.FromSql($"SELECT \"breedId\", \"breedName\", \"subBreed\", \"officialName\" FROM \"Breeds\" where \"breedId\" = {breedId}").ToList();
    return breeds;
});
#endregion

#region Users

app.MapPost("/register", (User usr, PTDb db) =>
{
    db.Users.Add(usr);
    db.SaveChanges();
});

app.MapPost("/login", [AllowAnonymous] (User user, PTDb db) =>
{
    User? chk = db.Users.Where(u => u.userName == user.userName && u.password == user.password).FirstOrDefault();
    if (chk!=null && chk.userId>0)
    {
        var issuer = builder.Configuration["Jwt:Issuer"];
        var audience = builder.Configuration["Jwt:Audience"];
        var key = Encoding.ASCII.GetBytes
        (builder.Configuration["Jwt:Key"]);
        var tokenDescriptor = new SecurityTokenDescriptor
        {
            Subject = new ClaimsIdentity(new[]
            {
                new Claim("Id", Guid.NewGuid().ToString()),
                new Claim(JwtRegisteredClaimNames.Email, user.userName),
                new Claim(JwtRegisteredClaimNames.Jti,
                Guid.NewGuid().ToString())
            }),
            Expires = DateTime.UtcNow.AddMinutes(5),
            Issuer = issuer,
            Audience = audience,
            SigningCredentials = new SigningCredentials
            (new SymmetricSecurityKey(key),
            SecurityAlgorithms.HmacSha512Signature)
        };
        var tokenHandler = new JwtSecurityTokenHandler();
        var token = tokenHandler.CreateToken(tokenDescriptor);
        var stringToken = tokenHandler.WriteToken(token);

        LoginResponseDTO dto = new LoginResponseDTO()
        {
            token = stringToken,
            user = chk
        };

        return Results.Ok(dto);
    }
    return Results.Unauthorized();
});

#endregion



app.Run();