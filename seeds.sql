-- User table
CREATE TABLE application_user (
  id SERIAL PRIMARY KEY,
  email TEXT UNIQUE NOT NULL,
  creation_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT (now() at time zone 'utc')
);

-- Device table
CREATE TABLE device (
  id SERIAL PRIMARY KEY,
  user_id INT NOT NULL REFERENCES application_user(id) ON DELETE CASCADE,
  name TEXT UNIQUE NOT NULL,
  creation_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT (now() at time zone 'utc')
);

CREATE INDEX device_user_id ON device(user_id);

-- Event table
CREATE TABLE event (
  id SERIAL PRIMARY KEY,
  device_id INT NOT NULL REFERENCES device(id) ON DELETE CASCADE,
  value INT NOT NULL,
  creation_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT (now() at time zone 'utc')
);
CREATE INDEX event_timestamp_device_id ON event(device_id, creation_date DESC);

-- Declare random function
CREATE OR REPLACE FUNCTION random_between(low INT ,high INT)
   RETURNS INT AS $$
BEGIN
   RETURN floor(random()* (high-low + 1) + low);
END;
$$ language 'plpgsql' STRICT;


INSERT INTO application_user(email) VALUES('one@example.com');
INSERT INTO application_user(email) VALUES('two@example.com');
INSERT INTO application_user(email) VALUES('three@example.com');

-- Insert 200 devices per user
DO $$
DECLARE device_per_user INTEGER=200;
DECLARE user_index INTEGER=0;
DECLARE app_user application_user;
BEGIN
  FOR app_user IN (SELECT * FROM application_user) LOOP
    FOR i IN 1..device_per_user LOOP
      INSERT INTO device(user_id, name) VALUES(app_user.id, 'Device ' ||  user_index * device_per_user  + i);
    END LOOP;
    user_index := user_index + 1;
  END LOOP;
END $$;

-- Insert 10_000 events per device
DO $$
DECLARE eventsPerDevice INTEGER=10000;
DECLARE device device;
DECLARE randomTime TIMESTAMP;
DECLARE randomInterval TEXT;
DECLARE randomValue INTEGER;
DECLARE ten_days INTEGER = 86400 * 10;
BEGIN
  FOR device IN (SELECT * FROM device) LOOP
    FOR i IN 1..eventsPerDevice LOOP
      randomInterval := random_between(1, ten_days) || ' SECONDS';
      randomTime := now() - randomInterval::interval;
      randomValue := random_between(1, 1000);
      INSERT INTO event(device_id, value, creation_date) VALUES(device.id, randomValue, randomTime);
    END LOOP;
    raise notice 'Inserted % events for device : %', eventsPerDevice, device.id;
  END LOOP;
END $$;
