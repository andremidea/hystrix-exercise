# Hystrix Environment Provisioner

* * *
> ### Tecnologias

> * [Ansible](http://docs.ansible.com/)
> * [Boto](https://github.com/boto/boto)


* * *

## Configurando o Ansible

- Instalando (MAC)
    - $ brew update
    - $ brew install ansible
- Instalando (CentOS/Fedora)
    - $ sudo yum install ansible
    ou
    - $ pip install ansible

- Instale as dependências
    - brew install python


- Instale o boto
    - pip install boto
    - crie o arquivo ~/.boto com suas credenciais. [Credentials]
                                                   aws_access_key_id = 112123
                                                   aws_secret_access_key = aaddkk

## Recursos da AWS

- VPC - é criado um vpc com internet gateway e 1 subnet
- Security Groups 
 - Front: Portas 22, 443 e 80 liberados para acesso externo
 - Web: Porta 22 liberada para acesso externo e TCP all para o security group *Front*                                                   

## Front

### Descrição

Único ponto de acesso http, nele temos um nginx que faz um proxy reverso para o serviço que está em outra máquina e para o Dashboard.

**Components**

- nginx
- tomcat
- Hystrix Dashboard

## Web

### Descrição

Máquina que contém o serviço.

**Components**

- Java com o Tanuki Wrapper



### Como Utilizar - Novas Máquina

- Instale as depencências do projeto, `ansible-galaxy install -r requirements`
- Altere as configurações do projeto no arquivo ** front/group_vars/all **
- Adicione a chave da AWS no bash ` ssh-add ~/.ssh/chave.pem `
- Execute o playbook ` ansible-playbook provisioning.yml -i inventory
    - provisioning.yml é o playbook
    - **-i** é o arquivo de inventário
- Após isso a máquina será criada na AWS e terá inicio o provisionamento.

- Após a criação das instâncias  adicione-as no inventory do ansible, eles estão em front/inventory ex: 

`[front]
172.16.0.71
[web]
172.16.0.71`
 
-
### Como Utilizar - Máquina Existente

- Mesmo processo anterior, só pule a parte do provisionamento e adicione o ip no inventory file correto.
`ansible-playbook provisioning.yml -i inventory

### Deploy do Servico

- É possivel provisionar apenas a parte do serviço novamente.
`ansible-playbook provisioning.yml -i inventory --tags="service-deploy"

