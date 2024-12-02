BEGIN TRANSACTION;
--users that came with the project
INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

-- breeds without any sub breeds
insert into breed(breed_name)
values  
('affenpinscher'),
('african'),
('airedale'),
('akita'),
('appenzeller'),
('basenji'),
('beagle'),
('bluetick'),
('borzoi'),
('bouvier'),
('boxer'),
('brabancon'),
('briard'),
('cavapoo'),
('chihuahua'),
('chow'),
('clumber'),
('cockapoo'),
('coonhound'),
('cotondetulear'),
('dachshund'),
('dalmatian'),
('dhole'),
('dingo'),
('doberman'),
('entlebucher'),
('eskimo'),
('germanshepherd'),
('groenendael'),
('havanese'),
('husky'),
('keeshond'),
('kelpie'),
('kombai'),
('komondor'),
('kuvasz'),
('labradoodle'),
('labrador'),
('leonberg'),
('lhasa'),
('malamute'),
('malinois'),
('maltese'),
('mexicanhairless'),
('mix'),
('newfoundland'),
('otterhound'),
('papillon'),
('pekinese'),
('pembroke'),
('pitbull'),
('pomeranian'),
('pug'),
('puggle'),
('pyrenees'),
('redbone'),
('rottweiler'),
('saluki'),
('samoyed'),
('schipperke'),
('sharpei'),
('shiba'),
('shihtzu'),
('stbernard'),
('tervuren'),
('vizsla'),
('weimaraner'),
('whippet');

--these breeds have at least 1 subbreed
insert into breed (breed_name, sub_breed)
values  ('australian','kelpie'),
('australian','sheperd'),
('bakharwal','indian'),
('buhund','norwegian'),
('bulldog','boston'),
('bulldog','english'),
('bulldog','french'),
('bullterrier','staffordshire'),
('cattledog','australian'),
('chippiparai','indian'),
('collie','border'),
('corgi','cardigan'),
('dane','great'),
('danish','swedish'),
('deerhound','scottish'),
('elkhound','norwegian'),
('finnish','lapphund'),
('frise','bichon'),
('gaddi','indian'),
('greyhound','indian'),
('greyhound','italian'),
('hound','afghan'),
('hound','basset'),
('hound','blood'),
('hound','english'),
('hound','ibizan'),
('hound','plott'),
('hound','walker'),
('mastiff','bull'),
('mastiff','english'),
('mastiff','indian'),
('mastiff','tibetan'),
('mountain','bernese'),
('mountain','swiss'),
('mudhol','indian'),
('ovcharka','caucasian'),
('pariah','indian'),
('pinscher','miniature'),
('pointer','german'),
('pointer','germanlonghair'),
('poodle','medium'),
('poodle','miniature'),
('poodle','standard'),
('poodle','toy'),
('rajapalayam','indian'),
('retriever','chesapeake'),
('retriever','curly'),
('retriever','flatcoated'),
('retriever','golden'),
('ridgeback','rhodesian'),
('schnauzer','giant'),
('schnauzer','miniature'),
('segugio','italian'),
('setter','english'),
('setter','gordon'),
('setter','irish'),
('sheepdog','english'),
('sheepdog','indian'),
('sheepdog','shetland'),
('spaniel','blenheim'),
('spaniel','brittany'),
('spaniel','cocker'),
('spaniel','irish'),
('spaniel','japanese'),
('spaniel','sussex'),
('spaniel','welsh'),
('spitz','indian'),
('spitz','japanese'),
('springer','english'),
('terrier','american'),
('terrier','australian'),
('terrier','bedlington'),
('terrier','border'),
('terrier','cairn'),
('terrier','dandie'),
('terrier','fox'),
('terrier','irish'),
('terrier','kerryblue'),
('terrier','lakeland'),
('terrier','norfolk'),
('terrier','norwich'),
('terrier','patterdale'),
('terrier','russell'),
('terrier','scottish'),
('terrier','sealyham'),
('terrier','silky'),
('terrier','tibetan'),
('terrier','toy'),
('terrier','welsh'),
('terrier','westhighland'),
('terrier','wheaten'),
('terrier','yorkshire'),
('waterdog','spanish'),
('wolfhound','irish');

insert into trait (trait_name)
values ('hypoallergenic'),('test trait 1'),('hairless'),('likes to boogie'),('alien');

COMMIT TRANSACTION;
