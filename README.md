# Moltrio Environment Provisioner

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
    - pip install virtualenv

- Crie um virtualenv para o projeto
    - virtualenv /foo/bar
    - ative o env source /foo/bar/bin/activate

- Instale o boto
    - pip install boto
    - crie o arquivo ~/.boto com suas credenciais. [Credentials]
                                                   aws_access_key_id = 112123
                                                   aws_secret_access_key = aaddkk

- Caso queira provisionar a máquina no Ansible ao invés da aws aponte o ansible para usar o python do venv no seu inventory localhost. `localhost ansible_python_interpreter=/foo/bar/bin/python`
isso só é necessário para os playbooks que se comunicam com a AWS.


## Front

### Descrição

Máquina que ira conter, o Jenkins e o WebService em Sinatra. Também servirá de Bastion Host para as outras máquinas.

**Components**

- nginx
- jenkins
- ruby

### Como Utilizar - Nova Máquina

- Instale as depencências do projeto, `ansible-galaxy install -r requirements`
- Altere as configurações do projeto no arquivo ** front/group_vars/all **
- Adicione a chave da AWS no bash ` ssh-add ~/.ssh/chave.pem `
- Execute o playbook ` ansible-playbook provisioning.yml -i localhost
    - provisioning.yml é o playbook
    - **-i** é o arquivo de inventário, no caso da criação da máquina é localhost pois ainda não temos ela.    
- Após isso a máquina será criada na AWS e terá inicio o provisionamento dela.

- Após a criação da instância e adicione no inventory do ansible, eles estão em front/production ex: 
`[ec2hosts]
172.16.0.71`
 
-
### Como Utilizar - Máquina Existente

- Mesmo processo anterior, só pule a parte do provisionamento e adicione o ip no inventory file correto. Por fim execute o playbook build.yml
`ansible-playbook build.yml -i production