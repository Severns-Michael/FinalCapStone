namespace PetTinder.Service
{
    public class User
    {
        public int userId { get; set; }
        public String userName { get; set; }
        public String password { get; set; }
        public List<UserTrait> UserTraits { get; set; }

    }
}
