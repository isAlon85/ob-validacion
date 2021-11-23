INSERT INTO role(name, description) VALUES ('USER' , 'User role');
INSERT INTO role(name, description) VALUES ('ADMIN' , 'Admin role');
INSERT INTO  ob_user(username, password, email, name, surname, role_id) VALUES ('admin@validation.com', '$2a$10$tN1FIPgHOHl3Jmo0ZxVphux6g8553hO5ulUCx.sVf3KIK9iNI9xqu', 'admin@validation.com', null, null, 2);
