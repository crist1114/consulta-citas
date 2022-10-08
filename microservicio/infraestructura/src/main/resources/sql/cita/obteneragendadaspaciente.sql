select count(id) as total
from cita
where id_paciente =:id_paciente AND estado = 'NO_ATENDIDA'