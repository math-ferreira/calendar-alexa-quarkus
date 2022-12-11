CREATE TABLE "event_table"
(
  id SERIAL PRIMARY KEY,
  summary VARCHAR(50) NOT NULL,
  description VARCHAR(255) NOT NULL,
  html_link VARCHAR(255) NOT NULL,
  start_date_time TIMESTAMP NOT NULL,
  end_date_time TIMESTAMP NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT now(),
  attendees VARCHAR(255) DEFAULT NULL
);