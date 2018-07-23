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

-- Insert three devices per user
DO $$
DECLARE user_index INTEGER=0;
DECLARE app_user application_user;
BEGIN
  FOR app_user IN (SELECT * FROM application_user) LOOP
    FOR i IN 1..3 LOOP
      INSERT INTO device(user_id, name) VALUES(app_user.id, 'Device ' ||  user_index * 3  + i);
    END LOOP;
    user_index := user_index + 1;
  END LOOP;
END $$;

-- Insert 1000 events per device
DO $$
DECLARE device device;
DECLARE randomTime TIMESTAMP;
DECLARE randomInterval TEXT;
DECLARE randomValue INTEGER;
DECLARE ten_days INTEGER = 86400 * 10;
BEGIN
  FOR device IN (SELECT * FROM device) LOOP
    FOR i IN 1..1000 LOOP
      randomInterval := random_between(1, ten_days) || ' SECONDS';
      randomTime := now() - randomInterval::interval;
      randomValue := random_between(1, 1000);
      INSERT INTO event(device_id, value, creation_date) VALUES(device.id, randomValue, randomTime);
    END LOOP;
  END LOOP;
END $$;
