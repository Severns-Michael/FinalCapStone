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
		('Leo',24,7,2,null,0,3),
		('Lucky',7,5,2,null,1,5),
        ('Bucket',80,6,1,null,0,6),
        ('Shemp',64,3,3,null,0,4),
        ('Ghost',75,4,1,null,1,8);
		
insert into breed_trait (breed_id,trait_id)
values
(1,12),
(1,30),
(2,2),
(2,4),
(3,21),
(3,5),
(4,17),
(4,33),
(5,27),
(5,10),
(6,16),
(6,22),
(8,26),
(8,35),
(9,36),
(10,28),
(11,24),
(12,20),
(12,7),
(13,19),
(13,15),
(14,14),
(14,11),
(15,31),
(15,32),
(16,6),
(16,18),
(17,9),
(17,1),
(18,34),
(18,29),
(19,12),
(19,30),
(20,2),
(20,4),
(21,21),
(21,5),
(22,17),
(22,33),
(23,27),
(23,10),
(24,16),
(24,22),
(25,23),
(26,26),
(26,35),
(27,36),
(28,28),
(29,24),
(30,20),
(30,7),
(31,19),
(31,15),
(32,14),
(32,11),
(33,31),
(33,32),
(34,6),
(34,18),
(35,9),
(35,1),
(36,34),
(36,29),
(37,12),
(37,30),
(38,2),
(38,4),
(39,21),
(39,5),
(40,17),
(40,33),
(41,27),
(41,10),
(42,16),
(42,22),
(43,23),
(44,26),
(44,35),
(45,36),
(46,28),
(47,24),
(48,20),
(48,7),
(49,19),
(49,15),
(50,14),
(50,11),
(51,31),
(51,32),
(52,6),
(52,18),
(53,9),
(53,1),
(54,34),
(54,29),
(55,12),
(55,30),
(56,2),
(56,4),
(57,21),
(57,5),
(58,17),
(58,33),
(59,27),
(59,10),
(60,16),
(60,22),
(61,23),
(62,26),
(62,35),
(63,36),
(65,24),
(66,20),
(66,7),
(67,19),
(67,15),
(68,14),
(68,11),
(69,31),
(69,32),
(70,6),
(70,18),
(71,9),
(71,1),
(72,34),
(72,29),
(73,12),
(73,30),
(74,2),
(74,4),
(76,17),
(76,33),
(77,27),
(77,10),
(78,16),
(78,22),
(79,23),
(81,36),
(82,28),
(83,24),
(84,20),
(84,7),
(85,19),
(85,15),
(86,14),
(86,11),
(87,31),
(87,32),
(88,6),
(88,18),
(89,9),
(89,1),
(90,34),
(90,29),
(91,12),
(91,30),
(92,2),
(92,4),
(93,21),
(93,5),
(94,17),
(94,33),
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
