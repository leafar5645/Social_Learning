DROP DATABASE IF EXISTS proyecto_adoo;
create database proyecto_adoo;
use proyecto_adoo;
create table usuario (idusuario int primary key not null , correo varchar(100) unique not null , pass varchar(100), nombre varchar(150), tipo varchar(1), img varchar(200) );
create table curso (idcurso int primary key not null, nombre varchar(100), idcreador int , numinscritos int , foreign key(idcreador) references usuario(idusuario));
create table usuariocurso(idusuario int , idcurso int , foreign key(idusuario) references usuario(idusuario) , foreign key(idcurso)references curso(idcurso) , primary key(idusuario , idcurso));

create table publicacion(idpubli int not null primary key , idcurso int not null , idusuario int not null , likes int  , contenido varchar(500), mediaUrl varchar(200) , validacion boolean , foreign key(idcurso) references curso(idcurso), foreign key(idusuario) references usuario(idusuario));
create table comentarios(idcomen int not  null primary key , texto varchar (150) , idusuario int not null , idpubli int not null , foreign key (idusuario)references usuario(idusuario) ,foreign key (idpubli) references publicacion (idpubli) );
create table tema (idt int not null , nombre varchar(100), texto varchar(5000), idcurso int not null , foreign key (idcurso) references curso (idcurso), primary key(idt )   );
create table examen (idexamen int not null, calif int , idalumno int , idt int , fecha timeStamp, foreign key (idalumno)references usuario(idusuario) , foreign key(idt)references tema(idt) , primary key (idexamen , idalumno, idt) );
create table  pregunta (idpregunta int not null, idt int not null, pregunta varchar(300) , respuesta varchar(300) , foreign key(idt) references tema(idt) , primary key(idpregunta, idt) );

select* from usuario;