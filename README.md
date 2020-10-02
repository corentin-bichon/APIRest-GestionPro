# APIRest-GestionPro
<p>
Api Rest with SpringBoot for manage professional's information


For use swagger : http://localhost:8080/swagger-ui.html
<p>

<br><br>


<h4> Database : </h4>
<h5>
Generate DataBase
</h5>
<h6>
CREATE DATABASE `DBProfessional` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
</h6>

<h5>
Create Table and Insert default values
</h5>
<h6>
USE DBProfessional;

DROP TABLE IF EXISTS professional;

CREATE table professional (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  firstname VARCHAR(50) NOT NULL,
  email VARCHAR(50) , 
  address VARCHAR(50),
  phone VARCHAR(10), 
  job VARCHAR(50),
  PRIMARY KEY(id)
) ENGINE=InnoDB;

 </h6>

<h6>
USE DBProfessional;
DROP TABLE IF EXISTS patient;

CREATE table patient (
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(50) NOT NULL, 
firstname VARCHAR(50) NOT NULL,
email VARCHAR(50) ,
address VARCHAR(50), 
phone VARCHAR(10), 
PRIMARY KEY(id) )
ENGINE=InnoDB;
</h6>


<h6>

USE DBProfessional;
DROP TABLE IF EXISTS propat;

CREATE table propat (
rNum BIGINT NOT NULL AUTO_INCREMENT,
pat_id BIGINT NOT NULL,
pro_id BIGINT NOT NULL,
sickness VARCHAR(50), 
PRIMARY KEY(rNum),
FOREIGN KEY (pat_id) REFERENCES patient (id),
FOREIGN KEY (pro_id) REFERENCES professional (id))
ENGINE=InnoDB;

</h6>
</h6>