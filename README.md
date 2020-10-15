# Dev Meetups

Aplicação web e API REST, desenvolvida com Spring Boot, para divulgar encontros sobre desenvolvimento de software e tecnologia.

## Funcionalidades

A aplicação web do Dev Meetups permite:

- Cadastrar, alterar, remover e listar eventos com título, descrição e uma imagem
- Enviar dados ou notificações com o FCM ([Firebase Cloud Messaging](https://firebase.google.com/docs/cloud-messaging))
- Identificar os dispositivos cadastrados na aplicação
- Envio de mensagem de dados do FCM ao cadastrar evento

Na API REST podemos:

- Buscar todos eventos
- Inscrever em eventos com e-mail
- Cancelar inscrição de evento por e-mail
- Buscar eventos inscritos a partir de um e-mail
- Remover eventos
- Cadastrar dispositivos
- Buscar todos dispositivos
- Envio de mensagem de dados do FCM

## Como baixar

Você pode baixar o arquivo jar executável a partir [deste link](https://github.com/alura-cursos/dev-meetups-api/archive/runnable.zip).

## Como executar

Após clonar o projeto, dentro do diretório raiz, execute a task `bootRun` do Gradle. A primeira execução vai realizar o download de todas dependências necessárias.

> O uso do Wrapper é recomendado (gradlew ou gradlew.bat) para garantir a compatibilidade.

Também é possível executar o projeto via arquivo jar gerado pela task `build` que é armazenado no diretório **build/libs/file-name.jar**. Para executar faça o seguinte:

```
java -jar file-name.jar
```

> A build do projeto foi feita com base no Java 8, portanto, é recomendado usar o Java 8 para que tudo funcione como esperado.

## Modificando propriedades de inicialização

Por padrão o Spring Boot vai rodar a aplicação na porta `8080`. Caso queria modificar a porta, edite a seguinte propriedade no arquivo **application-dev.yml**:

```yaml
server:
  port: ${port:8081}
```

Na amostra acima, a aplicação vai operar na porta `8081`.

## Modificando propriedade durante a execução

Também é possível modificar as propriedades via command line durante a execução.

### **Via task `bootRun` do Gradle**

Com o Gradle você pode alterar os valores das propriedades por meio do comando `args`:

```
./gradlew bootRun --args='--server.port=8081'
```

### **Via arquivo jar gerado**

Caso execute pelo arquivo jar:

```
java -jar file-name.jar --server.port=8081
```

## Configurando o FCM

A aplicação web oferece funcionalidades para enviar mensagens de notificação ou dados por meio do FCM. Para isso é necessário configurar o servidor adicionando a chave do Firebase.

Para obter a chave do Firebase, vá até o console:

- Acesse as configurações (**Settings**) contas de serviço (**Service Accounts**)
- Clique em gerar nova chave privata (**Generate New Private Key**) e confirme em gerar chave (**Generate Key**)

Ao fazer esse procedimento, um arquivo JSON será baixado, armazene-o no diretório **firebase** que é criado ao rodar a aplicação no mesmo nível.

Para conferir se a configuração funcionou como esperado, acesse a página de mensagens, se aparecer o formulário para enviar mensagem de dados ou notificação, a configuração foi feita com sucesso.

Caso contrário, algum problema ocorreu, confira do servidor e veja se existe alguma mensagem sobre o possível problema.

## Log de execução

A aplicação registra logs no terminal de acordo com algumas ações realizadas, como acesso aos controllers ou services, que indicam classes, métodos e argumentos executados. 

Caso tiver algum problema, utilize o log para tentar identificar o possível problema e resolvê-lo ou relatar via issues.

## Mapeamento de end-points

Para acessar as funcionalidades por meio da API REST, são disponibilizados os seguintes end-points:

> A URL base da API é `/api`, portanto, todas as requisições mantém esse prefixo.

### Dispositivos

- `/devices`
    - **GET**: busca de todos dispositivos

        ```json
        [
            {
                "modelo": "motorola one zoom",
                "marca": "motorola",
                "token": "cavqLZjrSpaUIuSwx8XfRF:APA91bF8bfU-j2kC3EdPHCGdJtzKxOubJRy7QKeirNkFEofrtnNEBzEb2s7X3_j9xGN2PD5XOIXLJamC0D2Gluuo3Li2VGg0xvDiZNikNfGl-nT7vE24eU0bUay32Warsh4BFj-ZNXCu"
            },
            {
                "modelo": "sdk_gphone_x86",
                "marca": "google",
                "token": "cbp-UkpFRnKIBkEu9e7qzA:APA91bErqfmoaYiSyjYhhB6oyX9UVH25DffpumRbTTe1PxgcZgfRXqVU9-jN6lTtpCclv9QRrLMMa8yU8oTsELMFAgzCJE5dJ2dbKesV0PrtqKVtoxjp46SukSAfh3qT-rukCBmpNWuo"
            }
        ]
        ```

    - **POST**: cadastro de dispositivo
        - Recebe o dispositivo no corpo da requisição no seguinte formato

        ```json
        {
           "marca":"google",
           "modelo":"sdk_gphone_x86",
           "token":"cbp-UkpFRnKIBkEu9e7qzA:APA91bErqfmoaYiSyjYhhB6oyX9UVH25DffpumRbTTe1PxgcZgfRXqVU9-jN6lTtpCclv9QRrLMMa8yU8oTsELMFAgzCJE5dJ2dbKesV0PrtqKVtoxjp46SukSAfh3qT-rukCBmpNWuo"
        }
        ```

### Eventos

- `/events`
    - **GET**: busca de todos os eventos

        ```json
        [
            {
                "id": "b10b06ab-4b05-45e9-95ae-48b07b6b1108",
                "inscritos": 0,
                "titulo": "Começando com o Kotlin",
                "descricao": "Aprenda a escrever programas em Kotlin utilizando os principais conceitos de OO:\r\n\r\n- Crie classes, proprieadades e métodos\r\n- Saiba como utilizar herança e polimorfismo\r\n- Utilize composição e interfaces",
                "imagem": "https://images.pexels.com/photos/34153/pexels-photo.jpg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
            },
            {
                "id": "2fd024f4-423d-47e5-b5d2-4e3b7a19c5ef",
                "inscritos": 0,
                "titulo": "Desenvolvimento Android",
                "descricao": "Saiba como desenvolver Apps para a plataforma Android:\r\n\r\n- Conheça o projeto Android e o SDK\r\n- Saiba quais são os pré-requisitos para desenvolver Apps Android\r\n- Entenda quais são as principais desafios ao criar Apps",
                "imagem": "https://images.pexels.com/photos/1092644/pexels-photo-1092644.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
            }
        ]
        ```

- `/events/{id}`
    - **DELETE**: Remove o evento pelo id
        - O id do evento deve ser enviado como caminho da URL:

         `/events/2fd024f4-423d-47e5-b5d2-4e3b7a19c5ef`

- `/events/{id}/subscribe`
    - **GET**: Inscreve um e-mail no evento
        - O id do evento deve ser enviado como caminho da URL:

        `/events/2fd024f4-423d-47e5-b5d2-4e3b7a19c5ef/subscribe`

        - Recebe o e-mail para inscrição como corpo da requisição:

        ```json
        {
           "email":"teste@meetups.com"
        }
        ```

- `/events/{id}/subscribed`
    - **GET**: Busca inscrição a partir de um e-mail
        - O id deve ser enviado no caminho da requisição e o e-mail como parâmetro da requisição:

        `events/2fd024f4-423d-47e5-b5d2-4e3b7a19c5ef/subscribed?email=teste@meetups.com`

        - Retorna o evento indicando se está inscrito:

        ```json
        {
            "estaInscrito": false,
            "titulo": "Começando com o Git",
            "descricao": "Aprenda a versionar o seu código com o Git",
            "imagem": "https://images.pexels.com/photos/52608/pexels-photo-52608.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
            "id": "627d5dfc-843c-4772-8bff-da9d7d133b55",
            "inscritos": 0
        }
        ```

- `/events/{id}/unsubscribe`
    - **PUT**: Cancela a inscrição do e-mail
        - O id do evento deve ser enviado como caminho da URL:

         `/events/2fd024f4-423d-47e5-b5d2-4e3b7a19c5ef`

        - Recebe o e-mail para cancelamento da inscrição como corpo da requisição:

        ```json
        {
           "email":"teste@meetups.com"
        }
        ```

- `/events/subscriptions`
    - **GET**: Devolve todas as inscrições do e-mail
        - O e-mail deve ser enviado como parâmetro da requisição

        `events/subscriptions?email=teste@meetups.com`

        Retorna a lista de inscrições do e-mail:

        ```json
        [
           {
              "estaInscrito":true,
              "titulo":"computação na prática",
              "descricao":"aprenda técnicas de algoritmos e escreva seus primeiros scripts",
              "imagem":"https://images.pexels.com/photos/577585/pexels-photo-577585.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
              "id":"337fa97f-b2c8-4ef0-8cdc-b587514b6972",
              "inscritos":1
           },
           {
              "estaInscrito":true,
              "titulo":"Programação com Java",
              "descricao":"Aprenda a escrever programas em Java e saiba como executá-los",
              "imagem":"https://images.pexels.com/photos/1181298/pexels-photo-1181298.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
              "id":"bca350f8-4478-4a99-8bc0-40cb9e283f1a",
              "inscritos":1
           }
        ]
        ```