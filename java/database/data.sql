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
values ('Hypoallergenic'),('Family Dog'),('Likes to Boogie'),('Vocal'),('Swimmer'),
('Anxious'),('Intelligent'),('Hunting'),('Pedigree'),('Needs Yard'),('Requires Regular Grooming'),
('Working Dog'),('Cold Weather'),('Potential Health Issues'),('Pack Oriented'),('Good with Cats'),('Territorial'),('Drools'),
('Portable'),('Longhair');

insert into dog (dog_name,breed_id, age, size)
		values  
		('Rover',9,6,1),
		('Rocky',16,5,1),
		('Thaddeus',5,2,2),
		('Fido',12,7,2),
		('Darius',29,8,2),
		('Nova',11,9,3);
		
insert into breed_trait (breed_id,trait_id)
		values  (9,4),
				(11,5);

COMMIT TRANSACTION;
