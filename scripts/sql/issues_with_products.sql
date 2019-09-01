CREATE TABLE Issues
(
  issue_id         SERIAL PRIMARY KEY,
  reported_by      BIGINT UNSIGNED NOT NULL,
  product_id       BIGINT UNSIGNED,
  priority         VARCHAR(20),
  version_resolved VARCHAR(20),
  status           VARCHAR(20),
  issue_type       VARCHAR(10),              -- BUG or FEATURE
  attributes       TEXT            NOT NULL, -- all dynamic attributes for the row
  FOREIGN KEY (reported_by) REFERENCES Accounts (account_id),
  FOREIGN KEY (product_id) REFERENCES Products (product_id)
);

CREATE TABLE Issues
(
  issue_id         SERIAL PRIMARY KEY,
  reported_by      BIGINT UNSIGNED NOT NULL,
  product_id       BIGINT UNSIGNED,
  priority         VARCHAR(20),
  version_resolved VARCHAR(20),
  status           VARCHAR(20),
  FOREIGN KEY (reported_by) REFERENCES Accounts (account_id),
  FOREIGN KEY (product_id) REFERENCES Products (product_id)
);

CREATE TABLE Bugs
(
  issue_id         BIGINT UNSIGNED PRIMARY KEY,
  severity         VARCHAR(20),
  version_affected VARCHAR(20),
  FOREIGN KEY (issue_id) REFERENCES Issues (issue_id)
);

CREATE TABLE FeatureRequests
(
  issue_id BIGINT UNSIGNED PRIMARY KEY,
  sponsor  VARCHAR(50),
  FOREIGN KEY (issue_id) REFERENCES Issues (issue_id)
);
CREATE TABLE Bugs
(
  issue_id         SERIAL PRIMARY KEY,
  reported_by      BIGINT UNSIGNED NOT NULL,
  product_id       BIGINT UNSIGNED,
  priority         VARCHAR(20),
  version_resolved VARCHAR(20),
  status           VARCHAR(20),
  severity         VARCHAR(20), -- only for bugs
  version_affected VARCHAR(20), -- only for bugs
  FOREIGN KEY (reported_by) REFERENCES Accounts (account_id),
  FOREIGN KEY (product_id) REFERENCES Products (product_id)
);

CREATE TABLE FeatureRequests
(
  issue_id         SERIAL PRIMARY KEY,
  reported_by      BIGINT UNSIGNED NOT NULL,
  product_id       BIGINT UNSIGNED,
  priority         VARCHAR(20),
  version_resolved VARCHAR(20),
  status           VARCHAR(20),
  sponsor          VARCHAR(50), -- only for feature requests
  FOREIGN KEY (reported_by) REFERENCES Accounts (account_id),
  FOREIGN KEY (product_id) REFERENCES Products (product_id)
);
CREATE TABLE Issues
(
  issue_id         SERIAL PRIMARY KEY,
  reported_by      BIGINT UNSIGNED NOT NULL,
  product_id       BIGINT UNSIGNED,
  priority         VARCHAR(20),
  version_resolved VARCHAR(20),
  status           VARCHAR(20),
  issue_type       VARCHAR(10), -- BUG or FEATURE
  severity         VARCHAR(20), -- only for bugs
  version_affected VARCHAR(20), -- only for bugs
  sponsor          VARCHAR(50), -- only for feature requests
  FOREIGN KEY (reported_by) REFERENCES Accounts (account_id),
  FOREIGN KEY (product_id) REFERENCES Products (product_id)
);

