# Integrantes do Grupo
	Nome				RA
	
	Samuel Lima Martins	<-->	173820
	Julia Dias Luz		<-->	238761
	Alona Nicolau		<--> 	249664
	Geovanne Silva 		<--> 	250328
	Enzo Arilla 		<--> 	255704

# Descrição do Projeto:

Desenvolver uma aplicação Java completa que utilize uma janela principal (`JFrame`) com os elementos de interface convencionais, como barra de menus (`JMenuBar`), barra de status, título, ícone e outros componentes. A aplicação deverá cumprir os seguintes objetivos:

1.  Demonstrar a mecânica de ouvintes (implementação interna):  

	O projeto deve implementar corretamente os ouvintes para interações do usuário, incluindo menus, botões e outros componentes.

2. Ler arquivos e apresentar o conteúdo na tela conforme escolha do usuário:  

	O aplicativo deve permitir a leitura de arquivos de texto e exibir seu conteúdo em uma área da janela principal.

3. Mostrar o uso de threads e gráficos dinâmicos por meio de um fundo continuamente animado:  

	A janela principal deve exibir um fundo gráfico que se altera dinamicamente, demonstrando o uso de threads em conjunto com desenhos gráficos.

4. Usar diálogos padrão para abrir arquivos:  

	Deve ser utilizado o componente `JFileChooser` para permitir ao usuário selecionar e abrir arquivos no menu “Arquivo”.

5. Construir um diálogo de ajuda personalizado com imagens, texto rolável e botões:  

	A aplicação deve conter um diálogo de ajuda acessível pelo menu "Ajuda", incluindo imagens, texto rolável e botões interativos.


# Estrutura da Interface (Progresso):

- [x] Janela Principal (`JFrame`):  
	A janela principal deve conter barra de título, ícone da aplicação, barra de status e menus.

- [x] Barra de Menus (`JMenuBar`):  
	Deve conter três menus principais: Arquivo, Configuração e Ajuda.
	- [x] Menu Arquivo:
		- [x] Abrir Arquivo: Abre um diálogo para selecionar um arquivo de texto e exibir seu conteúdo.
		- [x] Fechar Arquivo: Fecha o arquivo atualmente aberto e limpa a área de texto da tela.
		- [x] -- separador --
		- [x] Sair: Encerra a aplicação.
	- [x] Menu Configuração:
		- [x] Padrões: Permite escolher padrões para o comportamento dinâmico do fundo.
		- [x] Cores: Altera as cores do fundo dinâmico.
		- [x] Velocidade: Ajusta a velocidade das animações do fundo.
	- [x] Menu Ajuda:
		- [x] Ajuda: Abre um diálogo personalizado com explicações sobre a aplicação.
		- [x] Sobre: Mostra informações sobre a aplicação (como versão e autores).
