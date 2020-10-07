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
	