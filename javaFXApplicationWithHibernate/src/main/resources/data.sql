CREATE TABLE user (
    id bigint NOT NULL AUTO_INCREMENT,
    login varchar(255),
    password varchar(255),
    accesslvl bigint,
    dateofcreation datetime,
    dateofmodification datetime,
    PRIMARY KEY (ID),
    FOREIGN KEY (accessLvl) REFERENCES access_level (id)
    )

CREATE TABLE access_level (
    id bigint NOT NULL auto_increment,
    title varchar(255),
    PRIMARY KEY (id)
)