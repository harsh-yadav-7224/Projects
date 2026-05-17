#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Book
{
    int id;
    char name[50];
    struct Book *next;
};

struct Book *head = NULL;

void addBook(int id, char name[])
{
    struct Book *newBook = (struct Book *)malloc(sizeof(struct Book));

    newBook->id = id;
    strcpy(newBook->name, name);
    newBook->next = head;
    head = newBook;
}

void displayBooks()
{
    struct Book *temp = head;

    while (temp != NULL)
    {
        printf("Book ID: %d\n", temp->id);
        printf("Book Name: %s\n\n", temp->name);
        temp = temp->next;
    }
}

int main()
{
    addBook(101, "C Programming");
    addBook(102, "Java Basics");

    printf("Library Books:\n\n");
    displayBooks();

    return 0;
}