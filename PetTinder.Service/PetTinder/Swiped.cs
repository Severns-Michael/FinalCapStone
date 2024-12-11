namespace PetTinder.Service
{
    public class Swiped
    {
        public int swipedId { get; set; }
        public int userId { get; set; }
        public User user { get; set; }
        public int breedId { get; set; }
        public Breed breed { get; set; }
        public string image { get; set; }
        public bool swipeYes { get; set; }
    }
}
