using Microsoft.EntityFrameworkCore;
using System.Data.Common;


namespace PetTinder.Service
{
    class PTDb:DbContext
    {
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        => optionsBuilder.UseNpgsql(@"Host=localhost:5432;Username=postgres;Password=postgres1;Database=PetTinder");

        public DbSet<Breed> Breeds { get; set; }
        public DbSet<Swiped> Swipeds { get; set; }
        public DbSet<Trait> Traits { get; set; }
        public DbSet<User> Users { get; set; }
        public DbSet<UserTrait> UserTraits { get; set; }
    }
}
