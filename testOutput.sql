SELECT COUNT(*)
INTO CNT_V
FROM CROWN.VALIDATION_ERROR_MESSAGE
WHERE ERROR_CODE = 12519;

IF CNT_V = 0 THEN
	INSERT INTO CROWN.VALIDATION_ERROR_MESSAGE (ERROR_CODE,NAME,LABEL,ACTIVE,SORT_ORDER,CATEGORY,SUB_CATEGORY,IS_WARNING,CREATE_TIME,UPDATE_TIME) values (12519,'CW 4.0','Pre-Dialysis Weight must be greater than zero (0).',1,707,2,1,0,to_date('14-MAY-12','DD-MON-RR'),to_date('14-MAY-12','DD-MON-RR'));
ELSE
	UPDATE CROWN.VALIDATION_ERROR_MESSAGE
		SET ERROR_CODE = 12519,  
NAME = 'CW 4.0',  
LABEL = 'Pre-Dialysis Weight must be greater than zero (0).',  
ACTIVE = 1,  
SORT_ORDER = 707,  
CATEGORY = 2,  
SUB_CATEGORY = 1,  
IS_WARNING = 0,  
CREATE_TIME = to_date('14-MAY-12','DD-MON-RR'),  
UPDATE_TIME = to_date('14-MAY-12','DD-MON-RR') 
	WHERE ERROR_CODE = 12519;
END IF;

-- reset the counter
CNT_V := 0;

SELECT COUNT(*)
INTO CNT_V
FROM CROWN.VALIDATION_ERROR_MESSAGE
WHERE ERROR_CODE = 12629;

IF CNT_V = 0 THEN
	INSERT INTO CROWN.VALIDATION_ERROR_MESSAGE (ERROR_CODE,NAME,LABEL,ACTIVE,SORT_ORDER,CATEGORY,SUB_CATEGORY,IS_WARNING,CREATE_TIME,UPDATE_TIME) values (12629,'CW 4.0','Serum BUN is mandatory when Clinic Weight, Creatinine Clearance, Dialysate Volume, Dialysate Urea Nitrogen, Dialysate Creatinine, Urine Volume, or Serum Creatinine is populated.',1,758,2,1,0,to_date('14-MAY-12','DD-MON-RR'),to_date('14-MAY-12','DD-MON-RR'));
ELSE
	UPDATE CROWN.VALIDATION_ERROR_MESSAGE
		SET ERROR_CODE = 12629,  
NAME = 'CW 4.0',  
LABEL = 'Serum BUN is mandatory when Clinic Weight, Creatinine Clearance, Dialysate Volume, Dialysate Urea Nitrogen, Dialysate Creatinine, Urine Volume, or Serum Creatinine is populated.',  
ACTIVE = 1,  
SORT_ORDER = 758,  
CATEGORY = 2,  
SUB_CATEGORY = 1,  
IS_WARNING = 0,  
CREATE_TIME = to_date('14-MAY-12','DD-MON-RR'),  
UPDATE_TIME = to_date('14-MAY-12','DD-MON-RR') 
	WHERE ERROR_CODE = 12629;
END IF;

-- reset the counter
CNT_V := 0;

SELECT COUNT(*)
INTO CNT_V
FROM CROWN.VALIDATION_ERROR_MESSAGE
WHERE ERROR_CODE = 13079;

IF CNT_V = 0 THEN
	INSERT INTO CROWN.VALIDATION_ERROR_MESSAGE (ERROR_CODE,NAME,LABEL,ACTIVE,SORT_ORDER,CATEGORY,SUB_CATEGORY,IS_WARNING,CREATE_TIME,UPDATE_TIME) values (13079,'CW 4.0','When Hepatitis C Screen Test field is ''''Other'''', then Other Hepatitis C Screen Test field must have a value.',1,1069,2,1,0,to_date('14-MAY-12','DD-MON-RR'),to_date('14-MAY-12','DD-MON-RR'));
ELSE
	UPDATE CROWN.VALIDATION_ERROR_MESSAGE
		SET ERROR_CODE = 13079,  
NAME = 'CW 4.0',  
LABEL = 'When Hepatitis C Screen Test field is ''''Other'''', then Other Hepatitis C Screen Test field must have a value.',  
ACTIVE = 1,  
SORT_ORDER = 1069,  
CATEGORY = 2,  
SUB_CATEGORY = 1,  
IS_WARNING = 0,  
CREATE_TIME = to_date('14-MAY-12','DD-MON-RR'),  
UPDATE_TIME = to_date('14-MAY-12','DD-MON-RR') 
	WHERE ERROR_CODE = 13079;
END IF;

-- reset the counter
CNT_V := 0;

SELECT COUNT(*)
INTO CNT_V
FROM CROWN.VALIDATION_ERROR_MESSAGE
WHERE ERROR_CODE = 12629;

IF CNT_V = 0 THEN
	INSERT INTO CROWN.VALIDATION_ERROR_MESSAGE (ERROR_CODE,NAME,LABEL,ACTIVE,SORT_ORDER,CATEGORY,SUB_CATEGORY,IS_WARNING,CREATE_TIME,UPDATE_TIME) values (12629,'CW 4.0','Serum BUN is mandatory when Clinic Weight, Creatinine Clearance, Dialysate Volume, Dialysate Urea Nitrogen, Dialysate Creatinine, Urine Volume, or
Serum Creatinine is populated.',1,758,2,1,0,to_date('09-APR-12','DD-MON-RR'),to_date('09-APR-12','DD-MON-RR'));
ELSE
	UPDATE CROWN.VALIDATION_ERROR_MESSAGE
		SET ERROR_CODE = 12629,  
NAME = 'CW 4.0',  
LABEL = 'Serum BUN is mandatory when Clinic Weight, Creatinine Clearance, Dialysate Volume, Dialysate Urea Nitrogen, Dialysate Creatinine, Urine Volume, or
Serum Creatinine is populated.',  
ACTIVE = 1,  
SORT_ORDER = 758,  
CATEGORY = 2,  
SUB_CATEGORY = 1,  
IS_WARNING = 0,  
CREATE_TIME = to_date('09-APR-12','DD-MON-RR'),  
UPDATE_TIME = to_date('09-APR-12','DD-MON-RR') 
	WHERE ERROR_CODE = 12629;
END IF;

-- reset the counter
CNT_V := 0;

