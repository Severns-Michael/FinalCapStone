start transaction;

drop table if exists dog,breed,trait,fh_user,breed_trait;

create table breed(
	breed_id serial,
	breed_name varchar,
	
	constraint pk_breed primary key (breed_id),
);

create table trait(
	trait_id serial,
	trait_name varchar,
	
	constraint pk_trait primary key (trait_id)
);

create table breed_trait(
	breed_id serial,
	trait_id serial,
	
	constraint pk_bt primary key (breed_id, trait_id),
	constraint fk_bt_breed foreign key (breed_id) references breed(breed_id),	
	constraint fk_bt_trait foreign key (trait_id) references trait(trait_id)	
);

insert into breed(breed_name)
		values  ('samoyed'),
				('husky'),
				('beagle'),
				('french bulldog');
				
				
commit transaction;

