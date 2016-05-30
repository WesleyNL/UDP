# UDP

Esse projeto foi realizado na disciplina de Sistemas Distribuídos do professor Evandro Luquini, FATEC Carapicuíba. É uma versão modificada do projeto: https://github.com/brunoapimentel/chat

# Considerações

É necessário o download da API de JSON: http://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core
(neste projeto foi utilizada a versão 2.6.x).

InputHandler.java:

Linha 28 - Verifica se o usuário não existe, mas deixa enviar a mensagem?

Linha 35 - Modificamos a classe para que encontre usuários não somente por IP, mas também por seu nickname.

Robot.java: O robô deixou de ser usado para inclusão de diferentes interfaces.

Não informa aos usuários a desconexão do usuário, permitindo assim enviar mensagem à pessoas offline.

# Como fazer a implementação em qualquer outra interface java:

Implementar o InputHandler para que assim seja possível enviar mensagens às pessoas.

Criar um Listener e informar como ele deve agir quando receber um evento (esse evento tem um método para recuperação de mensagem, denominado "getMessage()").

Crie e inicie a thread server.

Crie e inicie a thread client.

Para enviar uma mensagem é necessário chamar o método readInput(String) do InputHandler, a String enviada tem que estar no seguinte formato: [IP ou Nickname do alvo]/[Mensagem para enviar].

Exemplo:

"Nicholas/Essa mensagem é um teste"
"Wesley/Opa, enviando mensagem!"

Para mostrar uma mensagem é necessário pegar a instância de Dispatcher e executar o método dispatchEvent(new MessageEvent(String mensagem));

Exemplo:

Dispatcher.getInstance().dispatchEvent(new MessageEvent("Olá mundo"));
