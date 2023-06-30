INSERT INTO coolbook.branch(
	id, address_line1, address_line2, address_line3, branch_code,branch_name, contact_number, company_id)
	VALUES (1, 'Main Street', 'Athura','Bulathsinhala', 'BUL','Bulathsinhala', '0342283022', 1);

INSERT INTO coolbook.company(
	id, address_line1, address_line2, address_line3, company_code, company_name, contact_number)
	VALUES (1, 'Main Street', 'Athura','Bulathsinhala','FNC' ,'Furnico', '0342283022');

INSERT INTO coolbook.oauth_user(
	id, password, user_name, branch_id)
	VALUES (1, '$2a$10$2Rezb8ejYQPNzNF4muBSxeHiE9Y9ejWZH5kozJbcVQsc1dysKTQmC', 'furnico', 1);
		
INSERT INTO coolbook.product_category(
	id, parent_category, productcat_code, productcat_name)
	VALUES (0, 0, 'ALL', 'ALL');

	CREATE TABLE IF NOT EXISTS coolbook.company
    (
        id bigint NOT NULL,
        address_line1 character varying(255) COLLATE pg_catalog."default",
        address_line2 character varying(255) COLLATE pg_catalog."default",
        address_line3 character varying(255) COLLATE pg_catalog."default",
        company_code character varying(255) COLLATE pg_catalog."default",
        company_name character varying(255) COLLATE pg_catalog."default",
        contact_number character varying(255) COLLATE pg_catalog."default",
        status character varying(255) COLLATE pg_catalog."default",
        addressline1 character varying(255) COLLATE pg_catalog."default",
        addressline2 character varying(255) COLLATE pg_catalog."default",
        addressline3 character varying(255) COLLATE pg_catalog."default",
        companycode character varying(255) COLLATE pg_catalog."default",
        companyname character varying(255) COLLATE pg_catalog."default",
        contactnumber character varying(255) COLLATE pg_catalog."default",
        CONSTRAINT company_pkey PRIMARY KEY (id),
        CONSTRAINT uk_1i0ysfte36jiw1p6sicl8btto UNIQUE (companycode),
        CONSTRAINT uk_46jubpbtfae2gfb74a3x6qug7 UNIQUE (company_name),
        CONSTRAINT uk_h7w1mkrsh1wcg5dkv6wrvam5m UNIQUE (companyname),
        CONSTRAINT uk_i2jcjcejgnwuafxofwmgosd13 UNIQUE (company_code)
    )

    TABLESPACE pg_default;

    ALTER TABLE IF EXISTS coolbook.company
        OWNER to postgres;

        CREATE TABLE IF NOT EXISTS coolbook.branch
        (
            id bigint NOT NULL,
            address_line1 character varying(255) COLLATE pg_catalog."default",
            address_line2 character varying(255) COLLATE pg_catalog."default",
            address_line3 character varying(255) COLLATE pg_catalog."default",
            branch_code character varying(255) COLLATE pg_catalog."default",
            branch_name character varying(255) COLLATE pg_catalog."default",
            contact_number character varying(255) COLLATE pg_catalog."default",
            status character varying(255) COLLATE pg_catalog."default",
            company_id bigint,
            addressline1 character varying(255) COLLATE pg_catalog."default",
            addressline2 character varying(255) COLLATE pg_catalog."default",
            addressline3 character varying(255) COLLATE pg_catalog."default",
            branchcode character varying(255) COLLATE pg_catalog."default",
            branchname character varying(255) COLLATE pg_catalog."default",
            contactnumber character varying(255) COLLATE pg_catalog."default",
            CONSTRAINT branch_pkey PRIMARY KEY (id),
            CONSTRAINT uk2f38xw29waieb4r62jorkhane UNIQUE (company_id, branch_name, branch_code),
            CONSTRAINT fk14f9k065wqeubl6tl0gdumcp5 FOREIGN KEY (company_id)
                REFERENCES coolbook.company (id) MATCH SIMPLE
                ON UPDATE NO ACTION
                ON DELETE NO ACTION
        )

        TABLESPACE pg_default;

        ALTER TABLE IF EXISTS coolbook.branch
            OWNER to postgres;

            CREATE TABLE IF NOT EXISTS coolbook.oauth_user
            (
                id bigint NOT NULL,
                password character varying(255) COLLATE pg_catalog."default",
                status character varying(255) COLLATE pg_catalog."default",
                user_name character varying(255) COLLATE pg_catalog."default",
                branch_id bigint,
                username character varying(255) COLLATE pg_catalog."default",
                CONSTRAINT oauth_user_pkey PRIMARY KEY (id),
                CONSTRAINT uk_6lw2yg9lxukkl6ljhw5vou5in UNIQUE (username),
                CONSTRAINT uk_pfkjyjgi1ygi6wsg1txlwoy0v UNIQUE (user_name),
                CONSTRAINT fklpb2bx84vvqgrug3pa22g5q42 FOREIGN KEY (branch_id)
                    REFERENCES coolbook.branch (id) MATCH SIMPLE
                    ON UPDATE NO ACTION
                    ON DELETE NO ACTION
            )

            TABLESPACE pg_default;

            ALTER TABLE IF EXISTS coolbook.oauth_user
                OWNER to postgres;


	