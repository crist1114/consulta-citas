SELECT max(fecha_emision) as fecha_emision
FROM   historia
WHERE  id_paciente = :id_paciente