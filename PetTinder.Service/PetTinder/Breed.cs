namespace PetTinder.Service
{
    public class Breed
    {
        public int breedId { get; set; }
        public string breedName { get; set; }
        public string subBreed { get; set; }
        public string officialName { get; set; }
        public List<Trait> traits { get; set; }
    }
}
