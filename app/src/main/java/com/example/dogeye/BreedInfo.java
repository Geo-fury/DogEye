package com.example.dogeye;

import java.util.HashMap;
import java.util.Map;

public class BreedInfo {
    private static final Map<String, DogBreed> breedInfoMap = new HashMap<>();

    static {
        breedInfoMap.put("French Bulldog", new DogBreed("French Bulldog", "10-12 years", "Brindle, Fawn, White, Cream", "16-28 pounds", "11-12 inches", "Smooth", "Playful, Adaptable, Smart", "France", "The French Bulldog (French: Bouledogue Français) is a French breed of companion dog or toy dog. It appeared in Paris in the mid-nineteenth century, apparently the result of cross-breeding of Toy Bulldogs imported from England and local Parisian ratters. It is commonly kept as a pet, and is among the most frequently registered dogs in a number of countries including Australia, the United Kingdom, and the United States. The breed is susceptible to various health problems as a consequence of breeding for their distinctive appearance, especially the brachycephalic face and skin wrinkles. Source: Wikipedia", R.drawable.french_bulldog));
        breedInfoMap.put("German Shepherd", new DogBreed("German Shepherd", "9-13 years", "Black, Tan, Red", "50-90 pounds", "22-26 inches", "Double", "Confident, Courageous, Smart", "Germany", "The German Shepherd, also known in Britain as an Alsatian, is a German breed of working dog of medium to large size. The breed was developed by Max von Stephanitz using various traditional German herding dogs from 1899.\n" +
                "\n" +
                "It was originally bred as a herding dog, for herding sheep. It has since been used in many other types of work, including disability assistance, search-and-rescue, police work, and warfare. It is commonly kept as a companion dog, and according to the Fédération Cynologique Internationale had the second-highest number of annual registrations in 2013.\n Source: Wikipedia\n", R.drawable.german_shepherd));
        breedInfoMap.put("Labrador Retriever", new DogBreed("Labrador Retriever", "11-13 years", "Black, Chocolate, Yellow", "55-80 pounds", "21.5-24.5 inches", "Short", "Active, Friendly, Outgoing", "Canada", "The Labrador Retriever or simply Labrador is a British breed of retriever gun dog. It was developed in the United Kingdom from St. John's water dogs imported from the colony of Newfoundland (now a province of Canada) and was named after the Labrador region of that colony. It is among the most kept dogs in several countries, particularly in the European world.\n" +
                "\n" +
                "The Labrador is friendly, energetic, and playful. It was bred as a sporting and hunting dog but is widely kept as a companion dog. It may also be trained as a guide or assistance dog, or for rescue or therapy work.\n Source: Wikipedia\n", R.drawable.labrador_retriever));
        breedInfoMap.put("Golden Retriever", new DogBreed("Golden Retriever", "10-12 years", "Dark Golden, Golden, Light Golden", "55-75 pounds", "21-24 inches", "Double", "Intelligent, Friendly, Devoted", "Scotland", "The Golden Retriever is a Scottish breed of retriever dog of medium size. It is characterised by a gentle and affectionate nature and a striking golden coat. It is commonly kept as a pet and is among the most frequently registered breeds in several Western countries. It is a frequent competitor in dog shows and obedience trials; it is also used as a gun dog (a type of hunting dog for soft-mouthed retrieving of fowl) and may be trained for use as a guide dog.\n" +
                "\n" +
                "The breed was created by Sir Dudley Marjoribanks at his Scottish estate Guisachan in the late nineteenth century. He cross-bred Flat-coated Retrievers with Tweed Water Spaniels, with some further infusions of Red Setter, Labrador Retriever and Bloodhound. The breed was recognised by the Kennel Club in 1913, and during the interwar period spread to many parts of the world. Source: Wikipedia\n", R.drawable.golden_retriever));
        breedInfoMap.put("Siberian Husky", new DogBreed("Siberian Husky", "12-14 years", "Agouti & White, Black & White, Gray & White, Red & White, Sable & White, White, Brown & White, Black Tan & White, Black", "35-60 pounds", "20-23.5 inches", "Double", "Loyal, Outgoing, Mischievous", "Siberia", "The Siberian Husky is a medium-sized working sled dog breed. The breed belongs to the Spitz genetic family. It is recognizable by its thickly furred double coat, erect triangular ears, and distinctive markings, and is smaller than the similar-looking Alaskan Malamute.\n" +
                "\n" +
                "Siberian Huskies originated in Northeast Asia where they are bred by the Chukchi people of Siberia for sled pulling and companionship. It is an active, energetic, resilient breed, whose ancestors lived in the extremely cold and harsh environment of the Siberian Arctic. William Goosak, a Russian fur trader, introduced them to Nome, Alaska, during the Nome Gold Rush, initially as sled dogs to work the mining fields and for expeditions through otherwise impassable terrain. Today, the Siberian Husky is typically kept as a house pet, though they are still frequently used as sled dogs by competitive and recreational mushers. Source: Wikipedia\n", R.drawable.siberian_husky));
        breedInfoMap.put("Poodle", new DogBreed("Poodle", "10-18 years", "A Large Selection of Colors", "40-70 pounds", "Over 15 inches (standard)", "Curly", "Active, Proud, Very Smart", "German", "The Poodle, called the Pudel in German and the Caniche in French, is a breed of water dog. The breed is divided into four varieties based on size, the Standard Poodle, Medium Poodle, Miniature Poodle and Toy Poodle, although the Medium Poodle is not universally recognised. They have a distinctive thick, curly coat that comes in many colors and patterns, with only solid colors recognized by breed registries. Poodles are active and intelligent, and are particularly able to learn from humans. Poodles tend to live 10–18 years, with smaller varieties tending to live longer than larger ones.\n" +
                "\n" +
                "The Poodle likely originated in Germany, although the Fédération Cynologique Internationale (FCI, International Canine Federation) and a minority of cynologists believe it originated in France. Similar dogs date back to at least the 17th century. Larger Poodles were originally used by wildfowl hunters to retrieve game from water, while smaller varieties were once commonly used as circus performers. Poodles were recognized by both the Kennel Club of the United Kingdom and the American Kennel Club (AKC) soon after the clubs' founding. Since the mid-20th century, Poodles have enjoyed enormous popularity as pets and show dogs – Poodles were the AKC's most registered breed from 1960 to 1982, and are now the FCI's third most registered breed. Poodles are also common at dog shows, where they often sport the popularly recognized Continental clip, with face and rear clipped close, and tufts of hair on the hocks and tail tip. Source: Wikipedia\n", R.drawable.poodle));
        breedInfoMap.put("Chihuahua", new DogBreed("Chihuahua", "14-16 years", "A Large Selection of Colors", "Not Exceeding 6 pounds", "5-8 inches", "Smooth", "Charming, Graceful, Aggressive", "Mexico", "The Chihuahua[a] (or Spanish: Chihuahueño) is a Mexican breed of toy dog. It is named for the Mexican state of Chihuahua and is among the smallest of all dog breeds. It is usually kept as a companion animal or for showing. Source: Wikipedia", R.drawable.chihuahua));
        breedInfoMap.put("Pomeranian", new DogBreed("Pomeranian", "12-16 years", "A Large Selection of Colors", "3-7 pounds", "6-7 inches", "Double", "Inquisitive, Lively, Energetic", "Northern Poland and Germany", "The Pomeranian (also known as a Pom, Pommy or Pome) is a breed of dog of the Spitz type that is named for the Pomerania region in north-west Poland and north-east Germany in Central Europe. Classed as a toy dog breed because of its small size, the Pomeranian is descended from larger Spitz-type dogs, specifically the German Spitz.\n" +
                "\n" +
                "The breed has been made popular by a number of royal owners since the 18th century. Queen Victoria owned a particularly small Pomeranian, and consequently, the smaller variety became universally popular. During Queen Victoria's lifetime alone, the size of the breed decreased by half. As of 2017, in terms of registration figures, since at least 1998, the breed has ranked among the top fifty most popular breeds in the United States, and the current fashion for small dogs has increased their popularity worldwide. Source: Wikipedia\n", R.drawable.pomeranian));
        breedInfoMap.put("Rottweiler", new DogBreed("Rottweiler", "9-10 years", "Black & Rust, Black & Mahogany, Black & Tan", "80-135 pounds", "22-27 inches", "Smooth", "Loyal, Loving, Smart", "Germany", "The Rottweiler is a breed of domestic dog, regarded as medium-to-large or large. The dogs were known in German as Rottweiler Metzgerhund, meaning Rottweil butchers' dogs, because their main use was to herd livestock and pull carts laden with butchered meat to market. This continued until the mid-19th century when railways replaced droving. Although still used to herd stock in many parts of the world, Rottweilers are now also used as search and rescue dogs, guard dogs, and police dogs. Source: Wikipedia", R.drawable.rottweiler));
        breedInfoMap.put("Beagle", new DogBreed("Beagle", "10-15 years", "A Large Selection of Colors", "20-30 pounds", "13-15 inches", "Smooth", "Curious, Friendly, Alert", "Great Britain", "The beagle is a breed of small scent hound, similar in appearance to the much larger foxhound. The beagle was developed primarily for hunting hare, known as beagling. Possessing a great sense of smell and superior tracking instincts, the beagle is the primary breed used as a detection dog for prohibited agricultural imports and foodstuffs in quarantine around the world. The beagle is a popular pet due to its size and good temper.\n" +
                "\n" +
                "The modern breed was developed in Great Britain around the 1830s from several breeds, including the Talbot Hound, the North Country Beagle, the Southern Hound, and possibly the Harrier.\n" +
                "\n" +
                "Beagles have been depicted in popular culture since Elizabethan times in literature and paintings and more recently in film, television, and comic books. Source: Wikipedia\n", R.drawable.beagle));

    }

    public static DogBreed getBreedInfo(String breed) {
        return breedInfoMap.getOrDefault(breed, new DogBreed("Information not available for this breed."));
    }
}

class DogBreed {
    String name, lifespan, colors, weight, height, coat, temperament, origin, description;
    int imageResId;

    public DogBreed(String name, String lifespan, String colors, String weight, String height, String coat, String temperament, String origin, String description, int imageResId) {
        this.name = name;
        this.lifespan = lifespan;
        this.colors = colors;
        this.weight = weight;
        this.height = height;
        this.coat = coat;
        this.temperament = temperament;
        this.origin = origin;
        this.description = description;
        this.imageResId = imageResId;
    }

    public DogBreed(String infoUnavailable) {
        this.name = infoUnavailable;
        this.lifespan = "";
        this.colors = "";
        this.weight = "";
        this.height = "";
        this.coat = "";
        this.temperament = "";
        this.origin = "";
        this.description = "";
        this.imageResId = 0;
    }
}
