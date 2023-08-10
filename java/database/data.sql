BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role,family_id,first_name,last_name,activated,is_child) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER',1,'firstname','lastname',true,false);
INSERT INTO users (username,password_hash,role,family_id,first_name,last_name,activated,is_child) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN',2,'firstname2','lastname2',true,false);

COMMIT TRANSACTION;
