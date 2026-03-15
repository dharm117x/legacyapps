-- Create a sample table
CREATE TABLE AppUsers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert sample data
INSERT INTO AppUsers (name, email) VALUES ('Alice Smith', 'Alice@h2.com');
INSERT INTO AppUsers (name, email) VALUES ('Bob Jones', 'Bob@h2.com');
INSERT INTO AppUsers (name, email) VALUES ('Charlie Brown', 'Brown@h2.com');
