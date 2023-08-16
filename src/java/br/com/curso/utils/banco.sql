CREATE TABLE autor(
idautor serial primary key,
	descricao varchar(100) not null
);
insert into autor (descricao) VALUES ('Machado de Assis');



select * from autor;

create table editora(
   ideditora serial primary key,
	descricao varchar(100) not null
	
);

insert into editora(descricao) VALUES ('Editora Rocco');


select * from editora;


create table genero(
   idgenero serial primary key,
	descricao varchar (100) not null
);

insert into genero (descricao) VALUES('Fantasia');
SELECT * from genero;



create table livro(
	idlivro serial PRIMARY key,
        titulo varchar (100),
	autor int not null,
	editora int not null,
	genero int not null,
	ano int not null,
	comentario varchar (300) not null,
	constraint fk_autor FOREIGN key (autor) REFERENCES autor(idautor),
	CONSTRAINT fk_editora FOREIGN key (editora) REFERENCES editora (ideditora),
	CONSTRAINT fk_genero FOREIGN key (genero) REFERENCES genero (idgenero)
);