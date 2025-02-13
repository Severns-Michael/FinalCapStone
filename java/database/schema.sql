BEGIN TRANSACTION;

DROP TABLE IF EXISTS dog,breed,trait,breed_trait,users,adoption_agency,preview_dogs;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

create table breed(
	breed_id serial,
	breed_name varchar NOT NULL,
	sub_breed varchar,
	official_name varchar,
	
	constraint pk_breed primary key (breed_id)
);

create table trait(
	trait_id serial,
	trait_name varchar NOT NULL,
	
	constraint pk_trait primary key (trait_id)
);
--create dog table with dog_id, img, name, breed, age, size

create table breed_trait(
	breed_id serial,
	trait_id serial,
	
	constraint pk_bt primary key (breed_id, trait_id),
	constraint fk_bt_breed foreign key (breed_id) references breed(breed_id),	
	constraint fk_bt_trait foreign key (trait_id) references trait(trait_id)	
);
create table adoption_agency(
	    agency_id serial,
	    agency_name varchar,
	    agency_city varchar,
	    agency_state varchar,

	    constraint pk_aa primary key(agency_id)
	);
create table dog(
	dog_id serial,
	dog_name varchar,
	breed_id int,
	agency_id int,
	gender int,
	age int,
	size int,
	img varchar,

	constraint pk_dog primary key(dog_id),
	constraint fk_dog foreign key(breed_id) references breed(breed_id),
	constraint fk_dog_agency foreign key(agency_id) references adoption_agency(agency_id)
);
    Create table users_trait_yes(
        user_id serial,
        trait_id serial,


        constraint pk_ut primary key (user_id, trait_id),
        constraint fk_ut_user foreign key (user_id) references users(user_id),
        constraint fk_ut_trait foreign key (trait_id) references trait(trait_id)
    );
    Create table users_trait_no(
            user_id serial,
            trait_id serial,

            constraint pk_ut_no primary key (user_id, trait_id),
            constraint fk_ut_user_no foreign key (user_id) references users(user_id),
            constraint fk_ut_trait_no foreign key (trait_id) references trait(trait_id)
        );
	Create table user_swipe_breeds(
		user_id serial,
		breed_id serial,
		swiped_transaction_id serial,
		img varchar,
		is_yes boolean,

		constraint pk_usd primary key(swiped_transaction_id),
		constraint fk_usd_user foreign key (user_id) references users(user_id),
		constraint fk_usd_breed foreign key (breed_id) references breed(breed_id)
	);
	Create table preview_breeds(
	    preview_breed_id serial,
	    breed_id int,

	    constraint pk_preview_breed_id primary key(preview_breed_id),
	    constraint fk_breed_id foreign key (breed_id) references breed(breed_id)
	);
	

COMMIT TRANSACTION;
