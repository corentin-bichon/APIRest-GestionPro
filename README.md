# APIRest-GestionPro
<p>
Api Rest with SpringBoot for manage professionnel informations


For use swagger : http://localhost:8080/swagger-ui.html
<p>

<br><br>


<h4> Database : </h4>
<h5>
Generate DataBase
</h5>
<h6>
CREATE DATABASE `DBProfessionnel` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
</h6>

<h5>
Create Table and Insert default values
</h5>
<h6>
USE DBProfessionnel;

DROP TABLE IF EXISTS PRO;

CREATE table PRO (
  PRO_ID BIGINT NOT NULL AUTO_INCREMENT,
  PRO_NAME VARCHAR(50) NOT NULL,
  PRO_FIRSTNAME VARCHAR(50) NOT NULL,
  PRO_EMAIL VARCHAR(50) , 
  PRO_ADRESSE VARCHAR(50),
  PRO_PHONE VARCHAR(10), 
  PRO_JOB VARCHAR(50),
  PRIMARY KEY(PRO_ID)
) ENGINE=InnoDB;

INSERT INTO PRO (PRO_NAME,PRO_FIRSTNAME) VALUES
  ('John','RACHID'),
  ('Mike','TYSON'),
  ('Benny','BERTRAND');
  
 </h6>
