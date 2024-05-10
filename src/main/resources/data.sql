use portal_tech;

insert into tipo(nome) Select 'Usuário' where not exists (select 1 from tipo where nome = 'Usuário');
insert into tipo(nome) Select 'Técnico' where not exists (select 1 from tipo where nome = 'Técnico');
insert into tipo(nome) Select 'Administrador' where not exists (select 1 from tipo where nome = 'Administrador');

insert into setor(nome) Select 'Administrativo' where not exists (select 1 from setor where nome = 'Administrativo');
insert into setor(nome) Select 'Recursos Humanos' where not exists (select 1 from setor where nome = 'Recursos Humanos');
insert into setor(nome) Select 'Marketing' where not exists (select 1 from setor where nome = 'Marketing');
insert into setor(nome) Select 'Suporte' where not exists (select 1 from setor where nome = 'Suporte');
insert into setor(nome) Select ' ' where not exists (select 1 from setor where nome = ' ');

insert into status(nome) Select 'Chamado Aberto' where not exists (select 1 from status where nome = 'Chamado Aberto');
insert into status(nome) Select 'Em atendimento' where not exists (select 1 from status where nome = 'Em atendimento');
insert into status(nome) Select 'Finalizado' where not exists (select 1 from status where nome = 'Finalizado');
insert into status(nome) Select 'Cancelado' where not exists (select 1 from status where nome = 'Cancelado');
insert into status(nome) Select 'Aguardando técnico' where not exists (select 1 from status where nome = 'Aguardando técnico');

insert into prioridade(nome) Select 'Prioridade a definir' where not exists (select 1 from prioridade where nome = 'Prioridade a definir');

--insert into pessoa(nome,email,senha,id_setor,id_tipo) Select ('Técnico a definir','email','senha',5,2) where not exists (select 1 from pessoa where nome = 'Técnico a definir');




