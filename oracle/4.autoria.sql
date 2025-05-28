-- Trigger de auditoría para COMERCIANTE
CREATE OR REPLACE TRIGGER trg_aud_comerciante
BEFORE INSERT OR UPDATE ON COMERCIANTE
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        :NEW.fecha_actualizacion := SYSDATE;
        :NEW.usuario_actualizacion := USER;
    ELSIF UPDATING THEN
        :NEW.fecha_actualizacion := SYSDATE;
        :NEW.usuario_actualizacion := USER;
    END IF;
END;
/

-- Trigger de auditoría para ESTABLECIMIENTO
CREATE OR REPLACE TRIGGER trg_aud_establecimiento
BEFORE INSERT OR UPDATE ON ESTABLECIMIENTO
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        :NEW.fecha_actualizacion := SYSDATE;
        :NEW.usuario_actualizacion := USER;
    ELSIF UPDATING THEN
        :NEW.fecha_actualizacion := SYSDATE;
        :NEW.usuario_actualizacion := USER;
    END IF;
END;
/