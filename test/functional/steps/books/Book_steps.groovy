package steps.books

import books.Book
import books.pages.ListPage
import books.pages.NewPage
import books.pages.ShowPage
import data.Data

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^I open the book tracker$/) { ->
    to ListPage
    at ListPage
}

When(~/^I add "(.*?)"$/) { String bookTitle ->
    page.toNewPage ()
    at NewPage

    page.add (bookTitle)
}

Then(~/^I see "(.*?)"s details$/) { String bookTitle ->
    at ShowPage

    page.check (bookTitle)
}

Given(~/^I have already added "(.*?)"$/) { String bookTitle ->
    def params = Data.findByTitle(bookTitle)
    remote {
        def book = new Book()
        book.properties = params
        book.save()
        book.id
    }
}

When(~/^I view the book list$/) { ->
    to ListPage
    at ListPage
}

Then(~/^my book list contains "(.*?)"$/) { String bookTitle ->
    page.check(bookTitle, 0)
}
