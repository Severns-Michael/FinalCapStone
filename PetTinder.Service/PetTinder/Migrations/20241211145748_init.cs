using Microsoft.EntityFrameworkCore.Migrations;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;

#nullable disable

namespace PetTinder.Service.Migrations
{
    /// <inheritdoc />
    public partial class init : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Breeds",
                columns: table => new
                {
                    breedId = table.Column<int>(type: "integer", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    breedName = table.Column<string>(type: "text", nullable: false),
                    subBreed = table.Column<string>(type: "text", nullable: false),
                    officialName = table.Column<string>(type: "text", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Breeds", x => x.breedId);
                });

            migrationBuilder.CreateTable(
                name: "Traits",
                columns: table => new
                {
                    traitId = table.Column<int>(type: "integer", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    traitName = table.Column<string>(type: "text", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Traits", x => x.traitId);
                });

            migrationBuilder.CreateTable(
                name: "Users",
                columns: table => new
                {
                    userId = table.Column<int>(type: "integer", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    userName = table.Column<string>(type: "text", nullable: false),
                    password = table.Column<string>(type: "text", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Users", x => x.userId);
                });

            migrationBuilder.CreateTable(
                name: "BreedTrait",
                columns: table => new
                {
                    BreedsbreedId = table.Column<int>(type: "integer", nullable: false),
                    traitstraitId = table.Column<int>(type: "integer", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_BreedTrait", x => new { x.BreedsbreedId, x.traitstraitId });
                    table.ForeignKey(
                        name: "FK_BreedTrait_Breeds_BreedsbreedId",
                        column: x => x.BreedsbreedId,
                        principalTable: "Breeds",
                        principalColumn: "breedId",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_BreedTrait_Traits_traitstraitId",
                        column: x => x.traitstraitId,
                        principalTable: "Traits",
                        principalColumn: "traitId",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Swipeds",
                columns: table => new
                {
                    swipedId = table.Column<int>(type: "integer", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    userId = table.Column<int>(type: "integer", nullable: false),
                    breedId = table.Column<int>(type: "integer", nullable: false),
                    image = table.Column<string>(type: "text", nullable: false),
                    swipeYes = table.Column<bool>(type: "boolean", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Swipeds", x => x.swipedId);
                    table.ForeignKey(
                        name: "FK_Swipeds_Breeds_breedId",
                        column: x => x.breedId,
                        principalTable: "Breeds",
                        principalColumn: "breedId",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Swipeds_Users_userId",
                        column: x => x.userId,
                        principalTable: "Users",
                        principalColumn: "userId",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "UserTrait",
                columns: table => new
                {
                    userTraitId = table.Column<int>(type: "integer", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    userId = table.Column<int>(type: "integer", nullable: false),
                    traitId = table.Column<int>(type: "integer", nullable: false),
                    isYes = table.Column<bool>(type: "boolean", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_UserTrait", x => x.userTraitId);
                    table.ForeignKey(
                        name: "FK_UserTrait_Traits_traitId",
                        column: x => x.traitId,
                        principalTable: "Traits",
                        principalColumn: "traitId",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_UserTrait_Users_userId",
                        column: x => x.userId,
                        principalTable: "Users",
                        principalColumn: "userId",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_BreedTrait_traitstraitId",
                table: "BreedTrait",
                column: "traitstraitId");

            migrationBuilder.CreateIndex(
                name: "IX_Swipeds_breedId",
                table: "Swipeds",
                column: "breedId");

            migrationBuilder.CreateIndex(
                name: "IX_Swipeds_userId",
                table: "Swipeds",
                column: "userId");

            migrationBuilder.CreateIndex(
                name: "IX_UserTrait_traitId",
                table: "UserTrait",
                column: "traitId");

            migrationBuilder.CreateIndex(
                name: "IX_UserTrait_userId",
                table: "UserTrait",
                column: "userId");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "BreedTrait");

            migrationBuilder.DropTable(
                name: "Swipeds");

            migrationBuilder.DropTable(
                name: "UserTrait");

            migrationBuilder.DropTable(
                name: "Breeds");

            migrationBuilder.DropTable(
                name: "Traits");

            migrationBuilder.DropTable(
                name: "Users");
        }
    }
}
