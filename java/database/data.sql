BEGIN TRANSACTION;
--users that came with the project
INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

-- breeds without any sub breeds
insert into breed(breed_name,official_name)
values  
('affenpinscher','Affen Pinscher'),
('african','African'),
('airedale','Airedale'),
('akita','Akita'),
('appenzeller','Appenzeller'),
('basenji','Basenji'),
('beagle','Beagle'),
('bluetick','Bluetick'),
('borzoi','Borzoi'),
('bouvier','Bouvier'),
('boxer','Boxer'),
('brabancon','Brabancon'),
('briard','Briard'),
('cavapoo','Cavapoo'),
('chihuahua','Chihuahua'),
('chow','Chow Chow'),
('clumber','Clumber Spaniel'),
('cockapoo','Cockapoo'),
('coonhound','Coonhound'),
('cotondetulear','Cotondetulear'),
('dachshund','Dachshund'),
('dalmatian','Dalmatian'),
('dhole','Dhole'),
('dingo','Dingo'),
('doberman','Doberman'),
('entlebucher','Entlebucher Mountain Dog'),
('eskimo','American Eskimo Dog'),
('germanshepherd','German Sheperd'),
('groenendael','Groenendael'),
('havanese','Havanese'),
('husky','Husky'),
('keeshond','Keeshond'),
('kelpie','Kelpie'),
('kombai','Kombai'),
('komondor','Komondor'),
('kuvasz','Kuvasz'),
('labradoodle','Labradoodle'),
('labrador','Labrador'),
('leonberg','Leonberg'),
('lhasa','Lhasa'),
('malamute','Malamute'),
('malinois','Malinois'),
('maltese','Maltese'),
('mexicanhairless','Xoloitzcuintli'),
('mix','Mix'),
('newfoundland','Newfoundland'),
('otterhound','Otterhound'),
('papillon','Papillon'),
('pekinese','Pekingese'),
('pembroke','Pembroke Welsh Corgi'),
('pitbull','Mr. Worldwide'),
('pomeranian','Pomeranian'),
('pug','Pug'),
('puggle','Puggle'),
('pyrenees','Pyrenees'),
('redbone','Redbone'),
('rottweiler','Rottweiler'),
('saluki','Saluki'),
('samoyed','Samoyed'),
('schipperke','Schipperke'),
('sharpei','Sharpei'),
('shiba','Shiba Inu'),
('shihtzu','Shihtzu'),
('stbernard','St. Bernard'),
('tervuren','Tervuren'),
('vizsla','Vizsla'),
('weimaraner','Weimaraner'),
('whippet','Whippet');

--these breeds have at least 1 subbreed
insert into breed (breed_name, sub_breed, official_name)
values  
('australian','kelpie','Australian Kelpie'),
('australian','sheperd','Autstralian Sheperd'),
('bakharwal','indian','Bakharwal'),
('buhund','norwegian','Norwegian Buhund'),
('bulldog','boston','Boston Bulldog'),
('bulldog','english','English Bulldog'),
('bulldog','french','Frenchie'),
('bullterrier','staffordshire','Stafforshire Bullterrier'),
('cattledog','australian','Australian Cattle Dog'),
('chippiparai','indian','Indian Chippiparai'),
('collie','border','Border Collie'),
('corgi','cardigan','Cardigan Corgi'),
('dane','great','Great Dane'),
('danish','swedish','Danish-Swedish Farmdog'),
('deerhound','scottish','Scottish Deerhound'),
('elkhound','norwegian','Norwegian Elkhound'),
('finnish','lapphund','Finnish Lapphund'),
('frise','bichon','Bichon Frisé'),
('gaddi','indian','Gaddi kutta'),
('greyhound','indian','Rampur Greyhound'),
('greyhound','italian','Italian Greyhound'),
('hound','afghan','Afghan Hound'),
('hound','basset','Basset Hound'),
('hound','blood','Bloodhound'),
('hound','english','English Hound'),
('hound','ibizan','Ibizan Hound'),
('hound','plott','Plott Hound'),
('hound','walker','Walker Hound'),
('mastiff','bull','Bull Mastiff'),
('mastiff','english','English Mastiff'),
('mastiff','indian','Indian Mastiff'),
('mastiff','tibetan','Tibetan Mastiff'),
('mountain','bernese','Swiss Mountain Dog'),
('mountain','swiss','Bernese Mountain Dog'),
('mudhol','indian','Mudhol hound'),
('ovcharka','caucasian','Caucasian Shepherd Dog'),
('pariah','indian','Indian pariah dog'),
('pinscher','miniature','Miniature Pinscher'),
('pointer','german','German Pointer'),
('pointer','germanlonghair','Longhair German Pointer'),
('poodle','medium','Medium Poodle'),
('poodle','miniature','Miniature Poodle'),
('poodle','standard','Poodle'),
('poodle','toy','Toy Poodle'),
('rajapalayam','indian','Rajapalayam'),
('retriever','chesapeake','Chesapeae Retriever'),
('retriever','curly','Curly Retriever'),
('retriever','flatcoated','Flatcoated Retriever'),
('retriever','golden','Golden Retriever'),
('ridgeback','rhodesian','Rhodesian Ridgeback'),
('schnauzer','giant','Giant Schnauzer'),
('schnauzer','miniature','Miniature Schnauzer'),
('segugio','italian','Italian Segugio'),
('setter','english','English Setter'),
('setter','gordon','Gordon Setter'),
('setter','irish','Irish Setter'),
('sheepdog','english','English Sheepdog'),
('sheepdog','indian','Indian Sheepdog'),
('sheepdog','shetland','Shetland Sheepdog'),
('spaniel','blenheim','Blenheim Spaniel'),
('spaniel','brittany','Brittany Spaniel'),
('spaniel','cocker','Cocker Spaniel'),
('spaniel','irish','Irish Spaniel'),
('spaniel','japanese','Japanese Spaniel'),
('spaniel','sussex','Sussex Spaniel'),
('spaniel','welsh','Welsh Spaniel'),
('spitz','indian','Inidian Spitz'),
('spitz','japanese','Japanese Spitz'),
('springer','english','English Springer'),
('terrier','american','American Terrier'),
('terrier','australian','Australian Terrier'),
('terrier','bedlington','Bedlington Terrier'),
('terrier','border','Border Terrier'),
('terrier','cairn','Cairn Terrier'),
('terrier','dandie','Dandie Terrier'),
('terrier','fox','Fox Terrier'),
('terrier','irish','Irish Terrier'),
('terrier','kerryblue','Kerryblue Terrier'),
('terrier','lakeland','Lakeland Terrier'),
('terrier','norfolk','Norfolk Terrier'),
('terrier','norwich','Norwich Terrier'),
('terrier','patterdale','Patterdale Terrier'),
('terrier','russell','Jack Russell Terrier'),
('terrier','scottish','Scottish Terrier'),
('terrier','sealyham','Sealyham Terrier'),
('terrier','silky','Silky Terrier'),
('terrier','tibetan','Tibetan Terrier'),
('terrier','toy','Toy Terrier'),
('terrier','welsh','Welsh Terrier'),
('terrier','westhighland','West Highland Terrier'),
('terrier','wheaten','Wheaten Terrier'),
('terrier','yorkshire','Yorkshire Terrier'),
('waterdog','spanish','Spanish Waterdog'),
('wolfhound','irish','Irish Wolfhound');

insert into trait (trait_name)
values 
('Hypoallergenic'),
('Family Dog'),
('Likes to Boogie'),
('Vocal'),
('Swimmer'),
('Anxious'),
('Intelligent'),
('Hunting'),
('Pedigree'),
('Needs Yard'),
('Requires Regular Grooming'),
('Working Dog'),
('Cold Weather'),
('Potential Health Issues'),
('Pack Oriented'),
('Good with Cats'),
('Territorial'),
('Drools'),
('Portable'),
('Longhair'),
('Chill'),
('Active'),
('Playful'),
('Guard Dog'),
('Good with Kids'),
('Energetic'),
('Easy to Train'),
('Independent'),
('Friendly'),
('Strong'),
('Quiet'),
('Good with Other Dogs'),
('Adventurous'),
('Low Maintenance'),
('Shed-Free'),
('Loyal'),
('Calm'),
('Athletic'),
('High Energy'),
('Social');

insert into adoption_agency (agency_name, agency_city, agency_state)
values ('Adoption Circle','Columbus','Ohio'),('Just Choice','Columbus','Ohio');

insert into adoption_agency (agency_name, agency_city, agency_state)
values
('Columbus Dog Rescue', 'Columbus', 'OH'),
('Forever Friends Canine Adoption', 'Columbus', 'OH'),
('Paw Haven Adoption Center', 'Columbus', 'OH'),
('Buckeye Dog Shelter', 'Columbus', 'OH'),
('Happy Tails Rescue', 'Columbus', 'OH'),
('Safe Paws Adoption Agency', 'Columbus', 'OH'),
('Loyal Companions Rescue', 'Columbus', 'OH'),
('New Leash on Life Shelter', 'Columbus', 'OH'),
('Golden Hearts Canine Haven', 'Columbus', 'OH'),
('Dream Dogs Adoption Center', 'Columbus', 'OH');

insert into dog (dog_name,breed_id, age, size, img, gender, agency_id)
		values  
		('Rover',9,6,1,'https://images.dog.ceo/breeds/borzoi/n02090622_6894.jpg',0,1),
		('Rocky',16,5,1,'https://images.dog.ceo/breeds/chow/n02112137_16354.jpg',0,2),
		('Thaddeus',5,2,2,'https://images.dog.ceo/breeds/appenzeller/n02107908_691.jpg',0,3),
		('Fido',12,2,2,'https://images.dog.ceo/breeds/brabancon/n02112706_1065.jpg',0,4),
		('Darius',29,8,2,'https://images.dog.ceo/breeds/groenendael/n02105056_2685.jpg',0,5),
		('Nova',11,9,3,'https://images.dog.ceo/breeds/boxer/n02108089_78.jpg',1,6),
		('Kenzie',34,4,1,null,1,7),
		('Spot',87,4,1,null,0,8),
		('Kevin',90,5,3,null,0,9),
		('Fred',37,3,2,null,0,10),
        ('Lexi',82,6,1,null,1,1),
        ('Lola',98,5,3,null,1,2),
		('Leo',29,7,2,null,0,3),
		('Lucky',7,5,2,null,1,5),
        ('Bucket',80,6,1,null,0,6),
        ('Shemp',64,3,3,null,0,4),
        ('Ghost',75,4,2,null,1,8);
		
insert into breed_trait (breed_id,trait_id)
values
(1, 10),
(2, 5),
(2, 9),
(2, 12),
(3, 4),
(3, 15),
(4, 1),
(4, 6),
(4, 17),
(5, 2),
(5, 14),
(6, 7),
(6, 10),
(6, 18),
(8, 4),
(8, 11),
(8, 19),
(9, 5),
(9, 9),
(10, 1),
(10, 7),
(10, 12),
(11, 6),
(11, 14),
(11, 17),
(12, 15),
(12, 20),
(13, 2),
(13, 10),
(13, 18),
(14, 5),
(14, 9),
(15, 4),
(15, 7),
(15, 11),
(16, 1),
(16, 6),
(16, 16),
(17, 2),
(17, 14),
(18, 10),
(18, 17),
(19, 4),
(19, 12),
(19, 20),
(20, 5),
(20, 18),
(21, 1),
(21, 7),
(21, 14),
(22, 2),
(22, 9),
(22, 15),
(23, 11),
(23, 17),
(24, 4),
(25, 5),
(25, 10),
(25, 18),
(26, 1),
(26, 6),
(26, 19),
(27, 2),
(27, 12),
(27, 20),
(28, 7),
(28, 14),
(29, 4),
(29, 9),
(29, 15),
(30, 5),
(30, 11),
(31, 1),
(31, 10),
(31, 17),
(32, 2),
(32, 18),
(33, 6),
(33, 12),
(34, 4),
(34, 7),
(34, 19),
(35, 5),
(35, 14),
(36, 1),
(36, 9),
(36, 15),
(37, 2),
(37, 11),
(37, 20),
(38, 10),
(38, 17),
(39, 4),
(39, 18),
(40, 5),
(40, 7),
(40, 12),
(41, 1),
(41, 6),
(41, 19),
(42, 2),
(42, 14),
(43, 9),
(43, 15),
(44, 4),
(44, 11),
(44, 20),
(45, 5),
(45, 10),
(45, 17),
(46, 1),
(46, 18),
(47, 2),
(47, 7),
(47, 12),
(48, 6),
(48, 19),
(49, 4),
(49, 14),
(50, 5),
(50, 9),
(50, 15),
(51, 1),
(51, 10),
(51, 20),
(52, 2),
(52, 17),
(53, 6),
(53, 18),
(54, 4),
(54, 7),
(54, 12),
(55, 5),
(55, 19),
(56, 1),
(56, 9),
(56, 14),
(57, 2),
(57, 11),
(57, 15),
(58, 10),
(58, 20),
(59, 4),
(59, 17),
(60, 5),
(60, 6),
(60, 18),
(75, 3),
(64,13),
(80, 25),
(7, 8);

insert into preview_breeds (breed_id) values (1), (2), (3), (4), (5), (6), (7), (8), (9), (10);



-- insert into users_trait_yes(user_id, trait_id)
--         values
--         (1,5),
--         (1,1),
--         (1,3),
--         (2,4),
--         (2,2);
-- insert into users_trait_no(user_id, trait_id)
--         values
--         (1,6),
--         (1,7),
--         (1,8),
--         (2,9),
--         (2,10);

-- insert into user_swipe_breeds(user_id, breed_id, img, is_yes)
--         values
--         (1,5,'12345',true),
--         (1,6,'2351235',false),
--         (1,7,'235235',true),
--         (1,8,'idk',false),
--         (1,9,'087234',true),
--         (1,10,'qdfaef',false),
--         (1,11,'23rvaweg',true),
--         (1,12,'1234qwer',true),
--         (1,13,'sadfwefqwefw',true),
--         (1,14,'qef34tsfv',true),
--         (1,15,'sefasfwavvr',false),
--         (1,16,'false',false),
--         (1,17,'qwefcfqw4g',true),
--         (1,18,'dfqweqwetq23',true),
--         (1,19,'99fhqwef',true);


COMMIT TRANSACTION;
