create table torneo (
cod_torneo char (10) not null primary key,
nombre varchar(50) not null,
fecha_inicio date not null
);

create table equipo (
id_equipo char (10) not null primary key,
nombre varchar(50) not null,
color_bandera varchar(10) not null,
id_mascota char (10) not null -- fk
);

alter table equipo add constraint fk_id_mascota foreign key (id_mascota) references mascota (id_mascota);

create table mascota (
id_mascota char (10) not null primary key,
nombre varchar(50) not null
);

-- hay que crearle foreign keys a esta tabla
create table torneo_equipo (
    cod_torneo char (10) not null,
    id_equipo char (10) not null
);

alter table torneo_equipo add constraint pk_torneo_equipo primary key (cod_torneo, id_equipo);
alter table torneo_equipo add constraint fk_torneor_equipo_cod_torneo foreign key (cod_torneo) references torneo (cod_torneo);
alter table torneo_equipo add constraint fk_torneor_equipo_id_equipo foreign key (id_equipo) references equipo (id_equipo);


create table entrenador (
    cedula_entrenador char(10) not null primary key,
    nombre varchar(50) not null,
    apellido1 varchar (50) not null,
    apellido2 varchar (50) not null,
    genero char(1) not null,
    licencia char(1) not null,
    id_equipo char (10) not null -- fk
)

alter table entrenador add constraint fk_entrenador_id_equipo foreign key (id_equipo) references equipo (id_equipo);

alter table entrenador add constraint chk_entrenador_genero check (genero in ('M', 'F'));
alter table entrenador add constraint chk_entreador_licencia check (licencia in ('A', 'B', 'C', 'D'));

create table asistente (
    cedula_asistente char(10) not null primary key,
    nombre varchar(50) not null,
    apellido1 varchar (50) not null,
    apellido2 varchar (50) not null,
    cedula_entrenador char (10) not null -- fk

)

alter table asistente add constraint fk_asistente_cedula_entrenador foreign key (cedula_entrenador) references entrenador (cedula_entrenador);

create table jugador (
    cedula_jugador char(10) not null primary key,
    nombre varchar(50) not null,
    apellido1 varchar (50) not null,
    apellido2 varchar (50) not null,
    cantidad_goles int not null default 0,
    edad int not null,
    numero_camisa int not null,
    id_equipo char (10) not null -- fk
);

alter table jugador add constraint fk_id_equipo foreign key (id_equipo) references equipo (id_equipo);

alter table jugador add constraint chk_goles check (cantidad_goles >= 0);
alter table jugador add constraint chk_edad check (edad >= 14);
alter table jugador add constraint chk_camisa check (numero_camisa >= 0);

--otra manera de agregar el default
-- alter table add constraint def_gol default 0 for cantidad_goless