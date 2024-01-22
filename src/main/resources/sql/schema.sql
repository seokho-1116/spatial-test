drop table if exists location3857;
drop table if exists location4326;
create table location3857
(
    id       bigint       not null auto_increment,
    name     varchar(255) not null,
    location POINT SRID 3857 not null,
    primary key (id)
) engine=InnoDB;
create table location4326
(
    id       bigint       not null auto_increment,
    name     varchar(255) not null,
    location POINT SRID 4326 not null,
    primary key (id)
) engine=InnoDB;
create table location_test
(
    id       bigint       not null auto_increment,
    name     varchar(255) not null,
    location POINT SRID 4326 not null,
    primary key (id)
) engine=InnoDB;
create spatial index location4326_location_idx on location4326(location);
create spatial index location3857_location_idx on location3857(location);
create spatial index location_test on location_test(location);