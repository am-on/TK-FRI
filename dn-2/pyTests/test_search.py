import pexpect

def test_search_by_id():
    baza = pexpect.pexpect()

    try:
        baza.send("add")

        baza.expect("Student ID: ")
        baza.send("631")

        baza.expect("First name: ")
        baza.send("ime")

        baza.expect("Last name: ")
        baza.send("priimek")

        baza.expect("Avg. grade: ")
        baza.send("8.3")

        baza.expect("OK")

        baza.send("search 631")

        baza.expect("\t631 | priimek, ime | 8.3")

        print("PASSED\ttest_search_by_id")

    except:
        print("FAILED\ttest_search_by_id")

    finally:
        baza.kill()


def test_search_wrong_id():
    baza = pexpect.pexpect()

    try:
        baza.send("add")

        baza.expect("Student ID: ")
        baza.send("631")

        baza.expect("First name: ")
        baza.send("ime")

        baza.expect("Last name: ")
        baza.send("priimek")

        baza.expect("Avg. grade: ")
        baza.send("8.3")

        baza.expect("OK")

        baza.send("search 1")
        baza.expect("Student does not exists")

        print("PASSED\ttest_search_wrong_id")

    except:
        print("FAILED\ttest_search_wrong_id")

    finally:
        baza.kill()


def test_search_invalid_id():
    baza = pexpect.pexpect()

    try:
        baza.send("add")

        baza.expect("Student ID: ")
        baza.send("631")

        baza.expect("First name: ")
        baza.send("ime")

        baza.expect("Last name: ")
        baza.send("priimek")

        baza.expect("Avg. grade: ")
        baza.send("8.3")

        baza.expect("OK")

        baza.send("search x")
        baza.expect("Invalid input data")

        print("PASSED\ttest_search_invalid_id")

    except:
        print("FAILED\ttest_search_invalid_id")

    finally:
        baza.kill()


def test_search_by_name():
    baza = pexpect.pexpect()

    try:
        baza.send("add")

        baza.expect("Student ID: ")
        baza.send("631")

        baza.expect("First name: ")
        baza.send("ime")

        baza.expect("Last name: ")
        baza.send("priimek")

        baza.expect("Avg. grade: ")
        baza.send("8.3")

        baza.expect("OK")

        baza.send("search")
        baza.expect("First name: ")
        baza.send("ime")
        baza.expect("Last name: ")
        baza.send("priimek")

        baza.expect("\t631 | priimek, ime | 8.3")

        print("PASSED\ttest_search_by_name")

    except:
        print("FAILED\ttest_search_by_name")

    finally:
        baza.kill()

def test_search_by_name_invalid():
    baza = pexpect.pexpect()

    try:
        baza.send("add")

        baza.expect("Student ID: ")
        baza.send("631")

        baza.expect("First name: ")
        baza.send("ime")

        baza.expect("Last name: ")
        baza.send("priimek")

        baza.expect("Avg. grade: ")
        baza.send("8.3")

        baza.expect("OK")

        baza.send("search")
        baza.expect("First name: ")
        baza.send("")
        baza.expect("Last name: ")
        baza.send("priimek")

        baza.expect("Invalid input data")

        print("PASSED\ttest_search_by_name_invalid")

    except:
        print("FAILED\ttest_search_by_name_invalid")

    finally:
        baza.kill()


def test_search_by_name_invalid_ln():
    baza = pexpect.pexpect()

    try:
        baza.send("add")

        baza.expect("Student ID: ")
        baza.send("631")

        baza.expect("First name: ")
        baza.send("ime")

        baza.expect("Last name: ")
        baza.send("priimek")

        baza.expect("Avg. grade: ")
        baza.send("8.3")

        baza.expect("OK")

        baza.send("search")
        baza.expect("First name: ")
        baza.send("ime")
        baza.expect("Last name: ")
        baza.send("")

        baza.expect("Invalid input data")

        print("PASSED\ttest_search_by_name_invalid_ln")

    except:
        print("FAILED\ttest_search_by_name_invalid_ln")

    finally:
        baza.kill()


def test_search_many():
    baza = pexpect.pexpect()

    try:
        baza.send("add")

        baza.expect("Student ID: ")
        baza.send("631")

        baza.expect("First name: ")
        baza.send("ime")

        baza.expect("Last name: ")
        baza.send("priimek")

        baza.expect("Avg. grade: ")
        baza.send("8")

        baza.expect("OK")

        baza.send("count")
        baza.expect("No. of students: 1")

        # second
        baza.send("add")

        baza.expect("Student ID: ")
        baza.send("632")

        baza.expect("First name: ")
        baza.send("ime1")

        baza.expect("Last name: ")
        baza.send("priimek1")

        baza.expect("Avg. grade: ")
        baza.send("8")

        baza.expect("OK")

        baza.send("count")
        baza.expect("No. of students: 2")

        # third
        baza.send("add")

        baza.expect("Student ID: ")
        baza.send("633")

        baza.expect("First name: ")
        baza.send("ime2")

        baza.expect("Last name: ")
        baza.send("priimek2")

        baza.expect("Avg. grade: ")
        baza.send("8")

        baza.expect("OK")

        baza.send("search 631")
        baza.expect("\t631 | priimek, ime | 8")

        baza.send("search")
        baza.expect("First name: ")
        baza.send("ime")
        baza.expect("Last name: ")
        baza.send("priimek")

        baza.expect("\t631 | priimek, ime | 8")

        print("PASSED\ttest_search_many")

    except:
        print("FAILED\ttest_search_many")

    finally:
        baza.kill()


if __name__ == "__main__":
    test_search_by_id()
    test_search_wrong_id()
    test_search_invalid_id()
    test_search_by_name()
    test_search_by_name_invalid()
    test_search_by_name_invalid_ln()
    test_search_many()
