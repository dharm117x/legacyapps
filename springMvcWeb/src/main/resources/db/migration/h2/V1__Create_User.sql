-- Create a new database user
CREATE USER "app_user" PASSWORD 'password123';

-- Grant permissions (In H2, ADMIN rights are often needed for schema changes)
ALTER USER "app_user" ADMIN TRUE;
