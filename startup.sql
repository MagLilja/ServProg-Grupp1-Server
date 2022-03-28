USE sample;

INSERT INTO Profile (id, firstName, userName)
VALUES (1, 'Magnus', 'Magnus');

INSERT INTO Message (id, textBody, profile_fk)
VALUES (1, 'Hej', 1);



SELECT *
FROM Message;