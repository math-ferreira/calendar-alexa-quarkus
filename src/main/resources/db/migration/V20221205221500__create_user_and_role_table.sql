CREATE TABLE "role_table"
(
  id SERIAL PRIMARY KEY,
  role_name VARCHAR(50) NOT NULL,
  description VARCHAR(255) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE "user_table"
(
  id SERIAL PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE "user_role_table"
(
  user_id SERIAL REFERENCES user_table (id),
  role_id SERIAL REFERENCES role_table (id)
);