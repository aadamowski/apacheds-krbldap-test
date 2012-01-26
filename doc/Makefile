
NAME=draft-ietf-ldapext-krb-wg-krbldap-00

all: $(NAME).txt $(NAME).html $(NAME).nr


$(NAME).txt: $(NAME).xml
	xml2rfc $(NAME).xml $(NAME).txt

$(NAME).html: $(NAME).xml
	xml2rfc $(NAME).xml $(NAME).html

$(NAME).nr: $(NAME).xml
	xml2rfc $(NAME).xml $(NAME).nr

clean:
	rm $(NAME).txt $(NAME).html $(NAME).nr
