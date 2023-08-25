INSERT INTO users VALUES(1, 'ediaz@acme.com', 'Efren', '$2a$10$0pk5lpsTtl6WNPIYgxRmIeWsEocBW3wBv2qKirhNqsg.B3pVG.a8O',  'ediaz');
INSERT INTO users VALUES(2, 'admin@acme.com', 'Admin', '$2a$10$VFB0No2mQNRVPV5BQNb48.h5g5LirRgjlc8HMQgZWAzvWBFgpZY32',  'admin');

INSERT INTO roles VALUES(1, 'ROLE_USER');
INSERT INTO roles VALUES(2, 'ROLE_ADMIN');

INSERT INTO users_roles VALUES(1, 1);
INSERT INTO users_roles VALUES(2, 2);