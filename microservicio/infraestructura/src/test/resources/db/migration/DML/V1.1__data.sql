INSERT INTO `paciente` (`id`, `nombre`, `tipo_paciente`) VALUES ('1092', 'cristian', 'CONTRIBUTIVO');
INSERT INTO `paciente` (`id`, `nombre`, `tipo_paciente`) VALUES ('1093', 'manuel', 'CONTRIBUTIVO');

INSERT INTO `cita` (`id`, `id_paciente`, `tipo_procedimiento`, `fecha`, `estado`, `valor`) VALUES (1, '1092', 'LIMPIEZA', '2022-10-19', 'NO_ATENDIDA', '55000');

INSERT INTO `historia` (`id`, `id_paciente`, `fecha_emision`, `ubicacion`) VALUES ('1', '1092', '2022-06-08', 'CASILLERO_1');
