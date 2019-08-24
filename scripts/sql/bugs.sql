Drop TABLE Bugs;
CREATE TABLE Bugs
(
  -- other columns
  status      VARCHAR(20) CHECK (status IN ('NEW', 'IN PROGRESS', 'FIXED')),
  status_enum ENUM ('NEW', 'IN PROGRESS', 'FIXED')
);
ALTER TABLE Bugs
  MODIFY COLUMN status
    ENUM ('NEW', 'IN PROGRESS', 'FIXED', 'DUPLICATE');

CREATE TABLE BugStatus
(
  status VARCHAR(20) PRIMARY KEY
);

INSERT INTO BugStatus (status)
VALUES ('NEW'),
       ('IN PROGRESS'),
       ('FIXED');

CREATE TABLE Bugs
(
  -- other columns
  status VARCHAR(20),
  FOREIGN KEY (status) REFERENCES BugStatus (status)
    ON UPDATE CASCADE
);

# INSERT INTO Bugs (status) VALUES ('NEW'); -- OK
#
# INSERT INTO Bugs (status) VALUES ('BANANA'); -- Error!


SELECT column_type
FROM information_schema.columns
WHERE table_schema = 'qa'
  AND table_name = 'Bugs'
  AND column_name = 'status';
