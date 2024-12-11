using System.Text.Json.Serialization;

namespace PetTinder.Service
{
    public class Trait
    {
        public int traitId { get; set; }
        public string traitName { get; set; }
        [JsonIgnore]
        public List<Breed> Breeds { get; }
        [JsonIgnore]
        public List<UserTrait> UserTraits { get; set; }
    }
}
