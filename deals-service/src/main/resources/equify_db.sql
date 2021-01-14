USE equify_db;

CREATE TABLE deals (
	deal_id VARCHAR(100) PRIMARY KEY NOT NULL,
    company_id VARCHAR(100) NOT NULL,
    deal_name VARCHAR(20) NOT NULL,
    deal_type VARCHAR(20) NOT NULL,
    origination_date DATE NOT NULL,
    deal_status VARCHAR(20) NOT NULL,
    fund_investing VARCHAR(20) NOT NULL,
	deal_team_lead VARCHAR(50) NOT NULL,
    equity_required INT NOT NULL,
    deal_currency VARCHAR(3) NOT NULL
    );

    show tables;

INSERT INTO deals (deal_id, company_id, deal_name, deal_type, origination_date, deal_status, fund_investing, deal_team_lead, equity_required, deal_currency)
VALUES ('f3831f8c-c338-4ebe-a82a-e2fc1d1ff78a', 'e254f8c-c442-4ebe-a82a-e2fc1d1ff78a', 'Project Bluebird', 'Growth Equity', '2019-02-11', 'Active', 'ECP Fund I', 'John Stanmore', 15, 'CAD');

INSERT INTO deals (deal_id, company_id, deal_name, deal_type, origination_date, deal_status, fund_investing, deal_team_lead, equity_required, deal_currency)
VALUES ('t9876f8c-c338-4abc-zf6a-ttt1', 'g254f8c-c567-4ebe-a82a-e2fc1d1ff78a', 'Project Spitfire', 'Growth Equity', '2017-03-17', 'Cold', 'ECP Fund II', 'Samantha Rose', 5, 'USD');

INSERT INTO deals (deal_id, company_id, deal_name, deal_type, origination_date, deal_status, fund_investing, deal_team_lead, equity_required, deal_currency)
VALUES ('38777179-7094-4200-9d61-edb101c6ea84', '442adb6e-fa58-47f3-9ca2-ed1fecdfe86c', 'Project Monet', 'Management Buyout', '2020-05-10', 'Active', 'ECP Fund I', 'Nelson Granger', 20, 'USD');

CREATE TABLE companies (
	company_id VARCHAR(100) PRIMARY KEY NOT NULL,
    company_name VARCHAR(50) NOT NULL,
    founded INT NOT NULL,
    country VARCHAR(20) NOT NULL,
    region VARCHAR(20) NOT NULL,
    sector VARCHAR(50) NOT NULL,
    enterprise_value DECIMAL(6, 2) NOT NULL
    );

INSERT INTO companies (company_id, company_name, founded, country, region, sector, enterprise_value)
VALUES ('e254f8c-c442-4ebe-a82a-e2fc1d1ff78a', 'Green Standards', 2009, 'Canada', 'North America', 'Recycling', 65.03);

INSERT INTO companies (company_id, company_name, founded, country, region, sector, enterprise_value)
VALUES ('g254f8c-c567-4ebe-a82a-e2fc1d1ff78a', 'Bear Mattress', 1993, 'USA', 'North America', 'Household Goods', 30.12);

INSERT INTO companies (company_id, company_name, founded, country, region, sector, enterprise_value)
VALUES ('442adb6e-fa58-47f3-9ca2-ed1fecdfe86c', 'ShipMonk', 2014, 'USA', 'North America', 'Support Services', 25.74);