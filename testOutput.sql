--Insert into CROWN.VALIDATION_ERROR_MESSAGE (ERROR_CODE,NAME,LABEL,ACTIVE,SORT_ORDER,CATEGORY,SUB_CATEGORY,IS_WARNING,CREATE_TIME,UPDATE_TIME) values (12519,'CW 4.0','Pre-Dialysis Weight must be greater than zero (0).',1,707,2,1,0,to_date('14-MAY-12','DD-MON-RR'),to_date('14-MAY-12','DD-MON-RR'))
SELECT COUNT(*)
INTO CNT_V
FROM CROWN.VALIDATION_ERROR_MESSAGE
WHERE ERROR_CODE = 12629;

IF CNT_V = 0 THEN
	INSERT INTO CROWN.VALIDATION_ERROR_MESSAGE (ERROR_CODE,NAME,LABEL,ACTIVE,SORT_ORDER,CATEGORY,SUB_CATEGORY,IS_WARNING,CREATE_TIME,UPDATE_TIME) values (12629,'CW 4.0','Serum BUN is mandatory when Clinic Weight, Creatinine Clearance, Dialysate Volume, Dialysate Urea Nitrogen, Dialysate Creatinine, Urine Volume, or Serum Creatinine is populated.',1,758,2,1,0,to_date('14-MAY-12','DD-MON-RR'),to_date('14-MAY-12','DD-MON-RR'))
ELSE
	UPDATE CROWN.VALIDATION_ERROR_MESSAGE
		SET ERROR_CODE = 12629 
		SET NAME = 'CW 4.0' 
		SET LABEL = 'Serum BUN is mandatory when Clinic Weight, Creatinine Clearance, Dialysate Volume, Dialysate Urea Nitrogen, Dialysate Creatinine, Urine Volume, or Serum Creatinine is populated.' 
		SET ACTIVE = 1 
		SET SORT_ORDER = 758 
		SET CATEGORY = 2 
		SET SUB_CATEGORY = 1 
		SET IS_WARNING = 0 
		SET CREATE_TIME = to_date('14-MAY-12','DD-MON-RR') 
		SET UPDATE_TIME = to_date('14-MAY-12','DD-MON-RR')) 
	WHERE ERROR_CODE = 12629;
END IF;

-- reset the counter
CNT_V := 0;


SELECT COUNT(*)
INTO CNT_V
FROM CROWN.VALIDATION_ERROR_MESSAGE
WHERE ERROR_CODE = 13079;

IF CNT_V = 0 THEN
	INSERT INTO CROWN.VALIDATION_ERROR_MESSAGE (ERROR_CODE,NAME,LABEL,ACTIVE,SORT_ORDER,CATEGORY,SUB_CATEGORY,IS_WARNING,CREATE_TIME,UPDATE_TIME) values (13079,'CW 4.0','When Hepatitis C Screen Test field is ''''Other'''', then Other Hepatitis C Screen Test field must have a value.',1,1069,2,1,0,to_date('14-MAY-12','DD-MON-RR'),to_date('14-MAY-12','DD-MON-RR'))
ELSE
	UPDATE CROWN.VALIDATION_ERROR_MESSAGE
		SET ERROR_CODE = 13079 
		SET NAME = 'CW 4.0' 
		SET LABEL = 'When Hepatitis C Screen Test field is ''''Other'''', then Other Hepatitis C Screen Test field must have a value.' 
		SET ACTIVE = 1 
		SET SORT_ORDER = 1069 
		SET CATEGORY = 2 
		SET SUB_CATEGORY = 1 
		SET IS_WARNING = 0 
		SET CREATE_TIME = to_date('14-MAY-12','DD-MON-RR') 
		SET UPDATE_TIME = to_date('14-MAY-12','DD-MON-RR')) 
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
Serum Creatinine is populated.',1,758,2,1,0,to_date('09-APR-12','DD-MON-RR'),to_date('09-APR-12','DD-MON-RR'))
ELSE
	UPDATE CROWN.VALIDATION_ERROR_MESSAGE
		SET ERROR_CODE = 12629 
		SET NAME = 'CW 4.0' 
		SET LABEL = 'Serum BUN is mandatory when Clinic Weight, Creatinine Clearance, Dialysate Volume, Dialysate Urea Nitrogen, Dialysate Creatinine, Urine Volume, or
Serum Creatinine is populated.' 
		SET ACTIVE = 1 
		SET SORT_ORDER = 758 
		SET CATEGORY = 2 
		SET SUB_CATEGORY = 1 
		SET IS_WARNING = 0 
		SET CREATE_TIME = to_date('09-APR-12','DD-MON-RR') 
		SET UPDATE_TIME = to_date('09-APR-12','DD-MON-RR')) 
	WHERE ERROR_CODE = 12629;
END IF;

-- reset the counter
CNT_V := 0;


