import pexpect

def test_remove_by_id():
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

        baza.send("remove 631")

        baza.expect("OK")

        baza.send("count")
        baza.expect("No. of students: 0")

        print("PASSED\ttest_remove_by_id")

    except:
        print("FAILED\ttest_remove_by_id")

    finally:
        baza.kill()


def test_remove_by_id_unexisting():
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

        baza.send("remove 632")

        baza.expect("Student does not exists")

        baza.send("count")
        baza.expect("No. of students: 1")

        print("PASSED\ttest_remove_by_id_unexisting")

    except:
        print("FAILED\ttest_remove_by_id_unexisting")

    finally:
        baza.kill()


def test_remove_wrong_id():
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

        baza.send("remove x")
        baza.expect("Invalid input data")

        baza.send("count")
        baza.expect("No. of students: 1")

        print("PASSED\ttest_remove_wrong_id")

    except:
        print("FAILED\ttest_remove_wrong_id")

    finally:
        baza.kill()


def test_remove_by_name():
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

        baza.send("remove")
        baza.expect("First name: ")
        baza.send("ime")
        baza.expect("Last name: ")
        baza.send("priimek")

        baza.expect("OK")

        baza.send("count")
        baza.expect("No. of students: 0")

        print("PASSED\ttest_remove_by_name")

    except:
        print("FAILED\ttest_remove_by_name")

    finally:
        baza.kill()


def test_remove_by_name_not_found():
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

        baza.send("remove")
        baza.expect("First name: ")
        baza.send("ime2")
        baza.expect("Last name: ")
        baza.send("priimek")

        baza.expect("Student does not exists")

        baza.send("count")
        baza.expect("No. of students: 1")

        print("PASSED\ttest_remove_by_name_not_found")

    except:
        print("FAILED\ttest_remove_by_name_not_found")

    finally:
        baza.kill()


def test_remove_by_name_invalid():
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

        baza.send("remove")
        baza.expect("First name: ")
        baza.send("")
        baza.expect("Last name: ")
        baza.send("priimek")

        baza.expect("Invalid input data")

        baza.send("count")
        baza.expect("No. of students: 1")

        print("PASSED\ttest_remove_by_name_invalid")

    except:
        print("FAILED\ttest_remove_by_name_invalid")

    finally:
        baza.kill()


def test_remove_by_name_invalid_ln():
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

        baza.send("remove")
        baza.expect("First name: ")
        baza.send("ime")
        baza.expect("Last name: ")
        baza.send("")

        baza.expect("Invalid input data")

        baza.send("count")
        baza.expect("No. of students: 1")

        print("PASSED\ttest_remove_by_name_invalid_ln")

    except:
        print("FAILED\ttest_remove_by_name_invalid_ln")

    finally:
        baza.kill()


def test_remove_many():
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

        baza.send("remove 631")
        baza.expect("OK")

        baza.send("remove")
        baza.expect("First name: ")
        baza.send("ime2")
        baza.expect("Last name: ")
        baza.send("priimek2")

        baza.expect("OK")

        baza.send("count")
        baza.expect("No. of students: 1")

        print("PASSED\ttest_remove_many")

    except:
        print("FAILED\ttest_remove_many")

    finally:
        baza.kill()


if __name__ == "__main__":
    test_remove_by_id()
    test_remove_by_id_unexisting()
    test_remove_wrong_id()
    test_remove_by_name()
    test_remove_by_name_not_found()
    test_remove_by_name_invalid()
    test_remove_by_name_invalid_ln()
    test_remove_many()
