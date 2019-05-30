
 DROP TABLE SUB_API_TBL;

CREATE TABLE IF NOT EXISTS SUB_API_TBL (
   id INT NOT NULL AUTO_INCREMENT primary key,
   parent_api_name VARCHAR(200) NOT NULL,
   api_name VARCHAR(200) NOT NULL, 
   api_descripton VARCHAR(2000) NOT NULL, 
   swagger_name VARCHAR(200) NOT NULL,
   api_image_url VARCHAR(500) NOT NULL,
   api_page_url VARCHAR(200) NOT NULL,
   api_devportal_link VARCHAR(1000) NOT NULL
);

 DROP TABLE API_DESC_TBL;

CREATE TABLE IF NOT EXISTS API_DESC_TBL (
   id INT NOT NULL AUTO_INCREMENT primary key,
   parent_api_name VARCHAR(200) NOT NULL,
   api_name VARCHAR(200) NOT NULL,
   swagger_name VARCHAR(100) NOT NULL,
   api_description VARCHAR(2000) NOT NULL, 
   api_version VARCHAR(20) NOT NULL, 
   api_owner VARCHAR(50) NOT NULL,
   api_status VARCHAR(50) NOT NULL,
   key_feature VARCHAR(4000),
   key_feature1 VARCHAR(400),
   key_feature2 VARCHAR(400),
   key_feature3 VARCHAR(400),
   key_feature4 VARCHAR(400),
   api_devportal_link VARCHAR(1000) NOT NULL
  );