ALTER TABLE AM_API_LC_EVENT ALTER COLUMN USER_ID VARCHAR(255) NOT NULL;
ALTER TABLE AM_APPLICATION ALTER COLUMN NAME VARCHAR(100);
ALTER TABLE IDN_OAUTH1A_REQUEST_TOKEN ALTER COLUMN SCOPE VARCHAR(767);
ALTER TABLE IDN_OAUTH1A_ACCESS_TOKEN ALTER COLUMN SCOPE VARCHAR(767);
ALTER TABLE IDN_OAUTH2_AUTHORIZATION_CODE ALTER COLUMN SCOPE VARCHAR(767);
ALTER TABLE IDN_OAUTH2_ACCESS_TOKEN ALTER COLUMN TOKEN_SCOPE VARCHAR(767);
ALTER TABLE AM_SUBSCRIBER ALTER COLUMN USER_ID VARCHAR(255);
ALTER TABLE AM_APPLICATION_KEY_MAPPING ADD CREATE_MODE VARCHAR(30) DEFAULT 'CREATED';
ALTER TABLE AM_APPLICATION_REGISTRATION ADD TOKEN_SCOPE VARCHAR(256) DEFAULT 'default';
ALTER TABLE AM_APPLICATION_REGISTRATION ADD INPUTS VARCHAR(256);
ALTER TABLE AM_APPLICATION ADD GROUP_ID VARCHAR(100);
ALTER TABLE AM_API ADD CONTEXT_TEMPLATE VARCHAR(256);
UPDATE AM_API SET CONTEXT_TEMPLATE = CONTEXT WHERE CONTEXT_TEMPLATE IS NULL;
UPDATE AM_API SET CONTEXT = concat(CONTEXT,concat('/',API_VERSION)) WHERE CONTEXT NOT LIKE concat('%', API_VERSION);
UPDATE IDN_OAUTH2_ACCESS_TOKEN SET TOKEN_SCOPE = concat(TOKEN_SCOPE, concat(' ', 'am_application_scope')) WHERE USER_TYPE = 'APPLICATION' AND TOKEN_SCOPE NOT LIKE '%am_application_scope%';
UPDATE IDN_OAUTH2_ACCESS_TOKEN SET VALIDITY_PERIOD = -2000 WHERE VALIDITY_PERIOD = 9223372036854775807;
ALTER TABLE AM_API ADD CREATED_BY VARCHAR(100);
ALTER TABLE AM_API ADD CREATED_TIME TIMESTAMP;
ALTER TABLE AM_API ADD UPDATED_BY VARCHAR(100);
ALTER TABLE AM_API ADD UPDATED_TIME TIMESTAMP;
ALTER TABLE AM_SUBSCRIBER ADD CREATED_BY VARCHAR(100);
ALTER TABLE AM_SUBSCRIBER ADD CREATED_TIME TIMESTAMP;
ALTER TABLE AM_SUBSCRIBER ADD UPDATED_BY VARCHAR(100);
ALTER TABLE AM_SUBSCRIBER ADD UPDATED_TIME TIMESTAMP;
ALTER TABLE AM_SUBSCRIPTION ADD CREATED_BY VARCHAR(100);
ALTER TABLE AM_SUBSCRIPTION ADD CREATED_TIME TIMESTAMP;
ALTER TABLE AM_SUBSCRIPTION ADD UPDATED_BY VARCHAR(100);
ALTER TABLE AM_SUBSCRIPTION ADD UPDATED_TIME TIMESTAMP;
ALTER TABLE AM_APPLICATION ADD CREATED_BY VARCHAR(100);
ALTER TABLE AM_APPLICATION ADD CREATED_TIME TIMESTAMP;
ALTER TABLE AM_APPLICATION ADD UPDATED_BY VARCHAR(100);
ALTER TABLE AM_APPLICATION ADD UPDATED_TIME TIMESTAMP;
