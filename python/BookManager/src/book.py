class Book:
    def __init__(self, title, author, year):
        if title is None or not isinstance(title, str) or title.strip() == "":
            raise ValueError("Title must be a non-empty string")
        if author is None or not isinstance(author, str) or author.strip() == "":
            raise ValueError("Author must be a non-empty string")
        if not isinstance(year, int) or year < 0 or year > 2100:
            raise ValueError("Year must be an integer between 0 and 2100")
        
        self.title = title.strip()
        self.author = author.strip()
        self.year = year

    def __str__(self):
        return f"{self.title} by {self.author} ({self.year})"
    
    def __eq__(self, value):
        if not isinstance(value, Book):
            return False
        return (self.title == value.title and
                self.author == value.author and
                self.year == value.year)
        
    def __hash__(self):
        return hash((self.title, self.author, self.year))