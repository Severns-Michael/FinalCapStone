namespace PetTinder.Service
{
    public class UserTrait
    {
        public int userTraitId { get; set; }
        public int userId { get; set; }
        public int traitId { get; set; }
        public bool isYes { get; set; } = false;
        public Trait Trait { get; set; }
        public User User { get; set; }
    } 
}
